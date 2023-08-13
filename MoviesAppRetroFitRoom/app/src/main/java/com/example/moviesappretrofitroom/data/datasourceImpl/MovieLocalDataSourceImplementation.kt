package com.example.moviesappretrofitroom.data.datasourceImpl

import androidx.room.Dao
import com.example.moviesappretrofitroom.data.api.TMDBService
import com.example.moviesappretrofitroom.data.datasource.MovieLocalDataSource
import com.example.moviesappretrofitroom.data.datasource.MovieRemoteDataSource
import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.data.models.MoviesListModel
import com.example.moviesappretrofitroom.data.roomdb.MovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieLocalDataSourceImplementation(
    private val movieDao: MovieDao,
) : MovieLocalDataSource {
    override suspend fun getRoomDBMovies(): List<MovieModel> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveRoomDBMovies(moviesList: List<MovieModel>) {

        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertAllMovies(moviesList)
        }

    }

    override suspend fun cleaAll() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }


}