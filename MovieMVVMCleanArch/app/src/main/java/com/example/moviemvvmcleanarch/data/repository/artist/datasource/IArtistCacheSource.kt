package com.example.moviemvvmcleanarch.data.repository.artist.datasource

import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.model.Movie

interface IArtistCacheSource {

      fun getArtistList():LiveData<List<Artist>>
      fun savePopularArtistToCache(list:List<Artist>)

}