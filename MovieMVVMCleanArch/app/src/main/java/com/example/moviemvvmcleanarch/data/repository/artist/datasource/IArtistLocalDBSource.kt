package com.example.moviemvvmcleanarch.data.repository.artist.datasource

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.model.Artist
import kotlinx.coroutines.flow.Flow

interface IArtistLocalDBSource {

    suspend fun getAllArtistFromDB(): Flow<List<Artist>>

    suspend fun saveArtistListToDB(artistsList: List<Artist>)

    suspend fun deleteAllArtistFromDB()


}