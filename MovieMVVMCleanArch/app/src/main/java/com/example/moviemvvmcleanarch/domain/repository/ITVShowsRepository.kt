package com.example.moviemvvmcleanarch.domain.repository

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShow
import retrofit2.Response

interface ITVShowsRepository {
    suspend fun getPopularTVShows(): Response<TVShow>
    suspend fun updateTVShowMovies(tvShowList:List<TVShow>)
//    suspend fun deleteTVShowMovies( )
}