package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository
import retrofit2.Response

class GetArtistUseCase(private val itvShowsRepository: ITVShowsRepository) {

    suspend fun getTVShowList(): List<TVShow> = itvShowsRepository.getPopularTVShows()

}