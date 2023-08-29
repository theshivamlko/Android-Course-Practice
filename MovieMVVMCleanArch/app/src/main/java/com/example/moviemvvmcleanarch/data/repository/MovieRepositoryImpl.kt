package com.example.moviemvvmcleanarch.data.repository

import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MovieRepositoryImpl(
    val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    val movieLocalDBDataSourceImpl: MovieLocalDBDataSourceImpl,
    val movieCacheDataSourceImpl: MovieCacheDataSourceImpl
) : IMovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {

        return getMoviesFromCache()
    }

    override suspend fun refreshPopularMovies( ):List<Movie> {

        val newList=getMoviesFromAPI()

        if(newList.size>0){

        }else{
            movieLocalDBDataSourceImpl.deleteAllMoviesFromDB()
            movieLocalDBDataSourceImpl.insertAllMoviesInDB(newList)
            movieCacheDataSourceImpl.savePopularMoviesToCache(newList)
        }

        return newList

    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSourceImpl.getPopularMoviesFromRemoteSource()
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
            movieLocalDBDataSourceImpl.getAllMoviesFromDB().flowOn(Dispatchers.IO).collect{
                if(it.size>0){
                    movieList=it
                }else{

                    movieList= getMoviesFromAPI()
                    CoroutineScope(Dispatchers.IO).launch {
                        movieLocalDBDataSourceImpl.insertAllMoviesInDB(movieList)
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

          movieList=  movieCacheDataSourceImpl.getPopularMoviesFromCache()

        } catch (e: Exception) {
            println("MovieRepositoryImpl ${e.stackTrace}")
        }

        if (movieList.size>0){
            return movieList
        }else{
            movieList=getMoviesFromLocalDB()
            movieCacheDataSourceImpl.savePopularMoviesToCache(movieList)
        }
        return movieList
    }



}