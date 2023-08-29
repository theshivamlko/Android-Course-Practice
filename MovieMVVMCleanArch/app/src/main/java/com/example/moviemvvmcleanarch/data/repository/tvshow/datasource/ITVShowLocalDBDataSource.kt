package com.example.moviemvvmcleanarch.data.repository.movie.datasource

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.TVShow
import kotlinx.coroutines.flow.Flow

interface ITVShowLocalDBDataSource {

    suspend fun getAllTVShowFromDB(): Flow<List<TVShow>>

    suspend fun insertAllTVShowInDB(movies:List<TVShow>)

    suspend fun deleteAllTVShowFromDB()

}