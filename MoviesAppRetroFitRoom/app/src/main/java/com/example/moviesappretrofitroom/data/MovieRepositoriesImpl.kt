package com.example.moviesappretrofitroom.data

import com.example.moviesappretrofitroom.data.datasource.MovieCacheDataSource
import com.example.moviesappretrofitroom.data.datasource.MovieLocalDataSource
import com.example.moviesappretrofitroom.data.datasource.MovieRemoteDataSource
import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.domain.repositories.MovieRepositories

class MovieRepositoriesImpl(
    private val moveRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepositories {
    override suspend fun getAllMovies(): List<MovieModel> {

        return getMoviesFromCache()
    }

    override suspend fun updateAllMovies(): List<MovieModel> {

        val newList = getMoviesFromAPI()
        movieLocalDataSource.cleaAll()
        movieLocalDataSource.saveRoomDBMovies(newList )
        movieCacheDataSource.saveMoviesToCache(newList )

        return newList
    }


    suspend fun getMoviesFromAPI(): List<MovieModel>  {
        var moveList: List<MovieModel>  = listOf()

        println("getMoviesFromAPI1")
        try {
            val response = moveRemoteDataSource.getAPIMovies()
            println("getMoviesFromAPI2 ${response.raw().request.url.toUrl()}")
            println("getMoviesFromAPI3 ${response.code()}")
            println("getMoviesFromAPI2 ${response.errorBody()}")
            val body = response.body()
            if (body != null) {
                moveList = body.moviesList
            }
        } catch (exception: Exception) {
            println("getMoviesFromAPI")
            exception.stackTrace
        }

        return moveList

    }

    suspend fun getMoviesFromRoom(): List<MovieModel>  {
        var moveList: List<MovieModel>  = listOf()

        try {
            moveList = movieLocalDataSource.getRoomDBMovies()

            if (moveList.isNotEmpty()) {
                return moveList
            } else {
                moveList = getMoviesFromAPI()
                movieLocalDataSource.saveRoomDBMovies(moveList!!)
                return moveList
            }

        } catch (exception: Exception) {
            println("getMoviesFromRoom")
            exception.stackTrace
        }

        return moveList
    }

    suspend fun getMoviesFromCache(): List<MovieModel> {

        var moveList: List<MovieModel>  = listOf()
        try {
            moveList = movieCacheDataSource.getMoviesFromCache()

            if (moveList.isNotEmpty()) {
                return moveList
            } else {
                moveList = getMoviesFromRoom()
                movieCacheDataSource.saveMoviesToCache(moveList)
                return moveList
            }

        } catch (exception: Exception) {
            println("getMoviesFromCache")
            exception.stackTrace
        }
        return moveList

    }

}