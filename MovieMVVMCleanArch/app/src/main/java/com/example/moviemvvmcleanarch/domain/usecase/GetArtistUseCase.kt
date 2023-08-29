package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetArtistUseCase(private val itvShowsRepository: ITVShowsRepository) {

    suspend fun getTVShowList(): Flow<List<TVShow>> = itvShowsRepository.getPopularTVShows()

}