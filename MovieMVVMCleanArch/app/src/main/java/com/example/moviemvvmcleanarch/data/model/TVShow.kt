package com.example.moviemvvmcleanarch.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable


@Entity("tvshows")
    data class TVShow(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,

        @SerializedName("backdrop_path")
        val backdropPath: String,

//        @SerializedName("first_air_date")
//        val firstAirDate: String,


    /*    @SerializedName("name")
        val name: String,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("popularity")
        val popularity: Double,

        @SerializedName("poster_path")
        val posterPath: String,*/

    )