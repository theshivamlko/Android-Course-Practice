package com.example.moviesappretrofitroom.data.models

import com.google.gson.annotations.SerializedName

data class MoviesListModel (

    @SerializedName("results")
    val moviesList: List<MovieModel>
)