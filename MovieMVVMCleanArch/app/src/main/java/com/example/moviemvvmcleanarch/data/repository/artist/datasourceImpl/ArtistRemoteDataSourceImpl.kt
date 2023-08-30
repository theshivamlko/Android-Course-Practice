package com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.ArtistsList
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArtistRemoteDataSourceImpl(private val tmdbService: TMDBService):IArtistRemoteDataSource {
    override suspend fun getPopularArtistFromRemoteSource():Flow<List<Artist>> = flow {
       val result = tmdbService.getPopularArtists()
        if(result.body()!=null){
            emit(result.body()!!.results)
        }
    }
}