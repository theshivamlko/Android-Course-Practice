package com.example.moviemvvmcleanarch.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvmcleanarch.data.model.TVShow


@Dao
interface TVShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTVShows(tvshows:List<TVShow>)

    @Query("SELECT * FROM tvshows")
    fun getAllTVShows(): List<TVShow>

    @Query("DELETE FROM tvshows")
    suspend fun deleteAllTVShows()

}