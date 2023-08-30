package com.example.moviemvvmcleanarch.data.repository.movie

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MovieRepositoryImpl(
    val iMovieRemoteDataSource: IMovieRemoteDataSource,
    val iMovieLocalDBDataSource: IMovieLocalDBDataSource,
    val iMovieCacheDataSource: IMovieCacheDataSource
) : IMovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {

        return getMoviesFromCache()
    }

    override suspend fun refreshPopularMovies( ):List<Movie> {

        val newList=getMoviesFromAPI()

        if(newList.size>0){

        }else{
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
            body?.let {
                movieList = body.results
            }

        } catch (e: Exception) {
            println("MovieRepositoryImpl ${e.stackTrace}")
        }
        return movieList
    }
    suspend fun getMoviesFromLocalDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            iMovieLocalDBDataSource.getAllMoviesFromDB().flowOn(Dispatchers.IO).collect{
                if(it.size>0){
                    movieList=it
                }else{

                    movieList= getMoviesFromAPI()
                    CoroutineScope(Dispatchers.IO).launch {
                        iMovieLocalDBDataSource.insertAllMoviesInDB(movieList)
                    }
                }
            }

        } catch (e: Exception) {
            println("MovieRepositoryImpl ${e.stackTrace}")
        }
        return movieList
    }


    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {

          movieList=  iMovieCacheDataSource.getPopularMoviesFromCache()

        } catch (e: Exception) {
            println("MovieRepositoryImpl ${e.stackTrace}")
        }

        if (movieList.size>0){
            return movieList
        }else{
            movieList=getMoviesFromLocalDB()
            iMovieCacheDataSource.savePopularMoviesToCache(movieList)
        }
        return movieList
    }



}