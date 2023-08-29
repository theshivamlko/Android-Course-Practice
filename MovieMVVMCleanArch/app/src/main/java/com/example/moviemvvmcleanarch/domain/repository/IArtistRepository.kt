package com.example.moviemvvmcleanarch.domain.repository

import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShow
import retrofit2.Response

interface IArtistRepository {
    suspend fun getPopularArtistShows(): Response<Artist>
    suspend fun updateArtistMovies(artistList: List<Artist>)
//    suspend fun deleteArtistMovies()
}