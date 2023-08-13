package com.example.moviesappretrofitroom.data.datasource

import com.example.moviesappretrofitroom.data.models.MoviesListModel
import retrofit2.Response

interface MovieRemoteDataSource {
//  Return data from API
    suspend fun getAPIMovies(): Response<MoviesListModel>

}