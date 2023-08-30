package com.example.moviemvvmcleanarch.data.repository.movie

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieRepositoryImpl(
    val iMovieRemoteDataSource: IMovieRemoteDataSource,
    val iMovieLocalDBDataSource: IMovieLocalDBDataSource,
    val iMovieCacheDataSource: IMovieCacheDataSource
) : IMovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {

        return getMoviesFromCache()
    }

    override suspend fun refreshPopularMovies(): List<Movie> {

        val newList = getMoviesFromAPI()

        if (newList.size > 0) {

        } else {
            iMovieLocalDBDataSource.deleteAllMoviesFromDB()
            iMovieLocalDBDataSource.insertAllMoviesInDB(newList)
            iMovieCacheDataSource.savePopularMoviesToCache(newList)
        }

        return newList

    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = iMovieRemoteDataSource.getPopularMoviesFromRemoteSource()
            val body = response.body()
            println("MovieRepositoryImpl getMoviesFromAPI ${body}")
            body?.let {
                movieList = body.results
            }

        } catch (e: Exception) {
            println("MovieRepositoryImpl error ${e.localizedMessage}")
            e.printStackTrace()        }
        return movieList
    }

    suspend fun getMoviesFromLocalDB(): List<Movie> {
          var movieList: List<Movie> = listOf()
        try {
            runBlocking(Dispatchers.IO) {

                val result =iMovieLocalDBDataSource.getAllMoviesFromDB()
                println("getMoviesFromLocalDB $result")

                    if (result.size > 0) {
                        movieList = result
                    } else {

                        movieList = getMoviesFromAPI()
                        println("getMoviesFromLocalDB $movieList")
                        CoroutineScope(Dispatchers.IO).launch {
                            iMovieLocalDBDataSource.insertAllMoviesInDB(movieList)
                        }
                    }


            }

        } catch (e: Exception) {
            println("MovieRepositoryImpl error ${e.localizedMessage}")
            e.printStackTrace()
        }

        return movieList

    }


    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {

            movieList = iMovieCacheDataSource.getPopularMoviesFromCache()

        } catch (e: Exception) {
            println("MovieRepositoryImpl error ${e.localizedMessage}")
            e.printStackTrace()        }

        if (movieList.size > 0) {
            return movieList
        } else {
            movieList = getMoviesFromLocalDB()
            println("MovieRepositoryImpl 5 getMoviesFromCache $movieList")
            iMovieCacheDataSource.savePopularMoviesToCache(movieList)
        }
        return movieList
    }


}