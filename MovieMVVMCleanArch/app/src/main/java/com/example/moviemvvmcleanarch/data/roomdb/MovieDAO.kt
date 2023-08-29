package com.example.moviemvvmcleanarch.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvmcleanarch.data.model.Movie
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies:List<Movie>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies():Flow<List<Movie>>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}