package com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistLocalDBSource
import com.example.moviemvvmcleanarch.data.roomdb.ArtistDAO
import kotlinx.coroutines.flow.Flow

class ArtistLocalDBSourceImpl(private val artistDAO: ArtistDAO) : IArtistLocalDBSource {
    override suspend fun getAllArtistFromDB(): Flow<List<Artist>> {
        return artistDAO.getAllArtist()
    }

    override suspend fun saveArtistListToDB(artistsList: List<Artist>) {
        artistDAO.insertAllArtist(artistsList)
    }

    override suspend fun deleteAllArtistFromDB() {
        artistDAO.deleteAllArtist()
    }


}