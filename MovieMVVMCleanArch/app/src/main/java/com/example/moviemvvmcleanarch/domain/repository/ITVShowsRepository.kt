package com.example.moviemvvmcleanarch.domain.repository

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShow
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITVShowsRepository {
    suspend fun getPopularTVShows(): Flow<List<TVShow>>
    suspend fun refreshPopularTvShows():Flow<List<TVShow>>
//    suspend fun deleteTVShowMovies( )
}