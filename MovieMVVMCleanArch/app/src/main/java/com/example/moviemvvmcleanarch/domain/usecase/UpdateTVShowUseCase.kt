package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository

class UpdateTVShowUseCase(private val itvShowsRepository: ITVShowsRepository) {

    suspend fun updateArtistList(tvShowsList: List<TVShow>) =
        itvShowsRepository.updateTVShow(tvShowsList)

}