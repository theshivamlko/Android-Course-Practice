package com.example.moviemvvmcleanarch.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvmcleanarch.data.model.TVShow


@Dao
interface TVShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTVShows(tvshows:List<TVShow>)

    @Query("SELECT * FROM movies")
    suspend fun getAllTVShows():LiveData<List<TVShow>>

    @Query("DELETE FROM movies")
    suspend fun deleteAllTVShows()

}