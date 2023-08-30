package com.example.moviemvvmcleanarch.data.repository.movie.datasource

import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShowsList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITVShowRemoteDataSource {

    suspend fun getPopularTVShowFromRemoteSource(): Response<TVShowsList>

}