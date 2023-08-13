package com.example.moviesappretrofitroom.data.datasource

import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.data.models.MoviesListModel
import retrofit2.Response

interface MovieLocalDataSource {

    suspend fun getRoomDBMovies(): List<MovieModel>
    suspend fun saveRoomDBMovies(moviesList:  List<MovieModel> )

    suspend fun cleaAll( )


}