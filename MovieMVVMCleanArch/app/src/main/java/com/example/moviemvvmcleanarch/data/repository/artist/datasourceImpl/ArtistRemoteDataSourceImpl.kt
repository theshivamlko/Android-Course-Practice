package com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.ArtistsList
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ArtistRemoteDataSourceImpl(private val tmdbService: TMDBService):IArtistRemoteDataSource {
    override suspend fun getPopularArtistFromRemoteSource(): Flow<ArtistsList> {
       return tmdbService.getPopularArtists()
    }
}