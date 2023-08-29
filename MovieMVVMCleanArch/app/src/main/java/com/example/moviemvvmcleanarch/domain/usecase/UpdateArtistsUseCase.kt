package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.domain.repository.IArtistRepository

class UpdateArtistsUseCase(private val iArtistRepository: IArtistRepository) {

    suspend fun refreshPopularArtists() = iArtistRepository.refreshPopularArtists()

}