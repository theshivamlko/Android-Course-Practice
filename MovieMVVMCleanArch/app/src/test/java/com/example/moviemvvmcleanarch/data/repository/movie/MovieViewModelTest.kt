package com.example.moviemvvmcleanarch.data.repository.movie

import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class MovieViewModelTest {



    lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp(){
        movieViewModel= mock()
        Mockito.`when`(movieViewModel.getMovies()).thenReturn()

    }


    @Test
    fun testViewModel(){

    }


}