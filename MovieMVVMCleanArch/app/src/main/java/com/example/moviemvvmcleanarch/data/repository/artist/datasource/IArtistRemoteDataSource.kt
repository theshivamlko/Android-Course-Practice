package com.example.moviemvvmcleanarch.data.repository.artist.datasource

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.ArtistsList
import com.example.moviemvvmcleanarch.data.model.MovieList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IArtistRemoteDataSource {

    suspend fun getPopularArtistFromRemoteSource(): Flow<List<Artist>>

}