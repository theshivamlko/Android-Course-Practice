package com.example.moviemvvmcleanarch.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.TVShow


@Database(entities = [Movie::class, TVShow::class, Artist::class], version = 1)
abstract class TMDBRoomDB : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
    abstract fun tvShowDAO(): TVShowDAO
    abstract fun artistDAO(): ArtistDAO


}