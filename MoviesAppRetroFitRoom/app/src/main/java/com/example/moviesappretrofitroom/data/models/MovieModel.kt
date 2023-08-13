package com.example.moviesappretrofitroom.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieModel(

    @PrimaryKey
    @ColumnInfo("id")
    @SerializedName("id")
    val movideID:Int,

    @SerializedName("poster_path")
    val posterPath:String,

    @SerializedName("release_date")
    val releaseDate:String,

    val overview:String,
    val title:String,

) {
}