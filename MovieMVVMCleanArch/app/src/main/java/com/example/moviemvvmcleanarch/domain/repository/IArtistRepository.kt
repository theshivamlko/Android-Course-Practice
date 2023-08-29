package com.example.moviemvvmcleanarch.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShow
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IArtistRepository {
    suspend fun getPopularArtist(): Flow<List<Artist>>
    suspend fun refreshPopularArtists():Flow<List<Artist>>
//    suspend fun deleteArtistMovies()
}