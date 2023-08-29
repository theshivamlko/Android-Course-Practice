package com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistCacheSource

class ArtistCacheSourceImpl : IArtistCacheSource {

    private lateinit var artistsList: MutableLiveData<List<Artist>>
    override fun getArtistList(): LiveData<List<Artist>> {
        return artistsList
    }

    override fun savePopularArtistToCache(list: List<Artist>) {
        artistsList.postValue(list)
    }


}