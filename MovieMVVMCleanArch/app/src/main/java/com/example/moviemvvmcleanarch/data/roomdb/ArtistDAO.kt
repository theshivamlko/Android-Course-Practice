package com.example.moviemvvmcleanarch.data.roomdb

import android.database.Observable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvmcleanarch.data.model.TVShow


@Dao
interface ArtistDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(artists:List<TVShow>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies():Observable<List<TVShow>>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}