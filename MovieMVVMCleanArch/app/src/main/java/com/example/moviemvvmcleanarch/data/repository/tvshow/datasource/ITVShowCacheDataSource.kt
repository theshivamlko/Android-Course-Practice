package com.example.moviemvvmcleanarch.data.repository.movie.datasource

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.TVShow

interface ITVShowCacheDataSource {

    suspend fun getPopularTVShowFromCache(): List<TVShow>
    suspend fun savePopularTVShowToCache(list:List<TVShow>)

}