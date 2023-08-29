package com.example.moviemvvmcleanarch.presentation.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateTVShowUseCase
import kotlinx.coroutines.flow.collect

class ArtistViewModel(
    val getArtistUseCase: GetArtistUseCase,
    val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {

    fun getArtists() = liveData<List<Artist>> {
        val movieList = getArtistUseCase.getTVShowList()

    }

    fun updateArtists(): LiveData<List<Artist>> {
        return liveData<List<Artist>> {
            val movieList = updateArtistsUseCase.refreshPopularArtists()
                .collect {
                    emit(it)
                }

        }
    }

}