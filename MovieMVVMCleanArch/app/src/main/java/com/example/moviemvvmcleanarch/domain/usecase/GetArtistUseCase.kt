package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.domain.repository.IArtistRepository
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetArtistUseCase(private val iArtistRepository: IArtistRepository) {

    suspend fun getTVShowList(): Flow<List<Artist>> = iArtistRepository.getPopularArtist()

}