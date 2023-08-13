package com.example.moviesappretrofitroom.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.moviesappretrofitroom.data.models.MovieModel
import retrofit2.http.Query


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies:List<MovieModel>)

    @androidx.room.Query("SELECT * FROM movies")
    suspend fun getAllMovies():List<MovieModel>

    @androidx.room.Query("DELETE FROM movies")
    suspend fun deleteAllMovies( )


}