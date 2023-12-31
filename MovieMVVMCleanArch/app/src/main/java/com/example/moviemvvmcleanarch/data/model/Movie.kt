package com.example.moviemvvmcleanarch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity("movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

//    @SerializedName("adult")
//    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,


    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("title")
    val title: String,

)
