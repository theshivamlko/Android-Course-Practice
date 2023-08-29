package com.example.moviemvvmcleanarch.data.roomdb

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.TVShow
import kotlinx.coroutines.flow.Flow


@Dao
interface ArtistDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArtist(artists:List<Artist>)

    @Query("SELECT * FROM movies")
    suspend fun getAllArtist(): Flow<List<Artist>>

    @Query("DELETE FROM movies")
    suspend fun deleteAllArtist()

}