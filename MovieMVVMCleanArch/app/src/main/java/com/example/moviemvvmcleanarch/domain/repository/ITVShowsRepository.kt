package com.example.moviemvvmcleanarch.domain.repository

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShow
import retrofit2.Response

interface ITVShowsRepository {
    suspend fun getPopularTVShows(): List<TVShow>
    suspend fun updateTVShow(tvShowList:List<TVShow>)
//    suspend fun deleteTVShowMovies( )
}