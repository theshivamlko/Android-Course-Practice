package com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl

import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.data.model.TVShowsList
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class TVShowRemoteDataSourceImpl(val tmdbService: TMDBService) : ITVShowRemoteDataSource {
    override suspend fun getPopularTVShowFromRemoteSource(): Flow<TVShowsList> {
        return  tmdbService.getPopularTVShows()
    }


}