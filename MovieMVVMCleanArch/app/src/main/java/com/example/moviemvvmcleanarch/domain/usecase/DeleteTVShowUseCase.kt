package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository

class DeleteTVShowUseCase(private val iTVShowsRepository: ITVShowsRepository) {

    suspend fun deleteTVShowList() = iTVShowsRepository.deleteTVShowMovies()

}