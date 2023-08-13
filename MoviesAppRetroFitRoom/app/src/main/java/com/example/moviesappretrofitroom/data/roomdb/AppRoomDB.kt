package com.example.moviesappretrofitroom.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesappretrofitroom.data.models.MovieModel


@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
abstract class AppRoomDB:RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

}