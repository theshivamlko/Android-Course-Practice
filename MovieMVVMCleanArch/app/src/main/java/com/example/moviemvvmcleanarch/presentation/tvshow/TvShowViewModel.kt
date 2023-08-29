package com.example.moviemvvmcleanarch.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetTVShowUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateTVShowUseCase
import kotlinx.coroutines.flow.collect

class TvShowViewModel(
    val getTVShowUseCase: GetTVShowUseCase,
    val updateTVShowUseCase: UpdateTVShowUseCase
) : ViewModel() {

    fun getMovies() = liveData<List<Movie>> {
        val movieList = getTVShowUseCase.getTVShowList()

    }

    fun updateMovies(): LiveData<List<TVShow>> {
        return liveData<List<TVShow>> {
            updateTVShowUseCase.refreshPopularTvShows().collect {
                emit(it)
            }

        }
    }

}