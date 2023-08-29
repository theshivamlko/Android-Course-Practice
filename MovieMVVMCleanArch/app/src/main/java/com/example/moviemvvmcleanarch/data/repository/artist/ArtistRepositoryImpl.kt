package com.example.moviemvvmcleanarch.data.repository.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistCacheSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistLocalDBSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.domain.repository.IArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class ArtistRepositoryImpl(
    val artistRemoteDataSourceImpl: ArtistRemoteDataSourceImpl,
    val artistLocalDBSourceImpl: ArtistLocalDBSourceImpl,
    val artistCacheSourceImpl: ArtistCacheSourceImpl

) : IArtistRepository {
    override suspend fun getPopularArtistShows(): LiveData<List<Artist>> {
        var artistList: MutableLiveData<List<Artist>> = MutableLiveData<List<Artist>>()

        var list = getArtistFromCache().value
        if (list != null) {
            artistList.postValue(list!!)
        } else {
            list = getArtistFromLocalDB().value
            if (list != null) {
                artistList.postValue(list!!)
            } else {
                list = getArtistsFromAPI().value
                if (list != null) {
                    artistLocalDBSourceImpl.deleteAllArtistFromDB()
                    artistLocalDBSourceImpl.saveArtistListToDB(list)
                    artistCacheSourceImpl.savePopularArtistToCache(list)
                    artistList.postValue(list!!)
                }
            }

        }
        return artistList
    }

    override suspend fun updateArtistMovies(artistList: List<Artist>) {
        TODO("Not yet implemented")
    }


    fun getArtistsFromAPI(): LiveData<List<Artist>> {

        return liveData<List<Artist>> {
            val response = artistRemoteDataSourceImpl.getPopularArtistFromRemoteSource()
                .flowOn(Dispatchers.IO).collect {
                    emit(it.results)
                }
        }


    }

    fun getArtistFromLocalDB(): LiveData<List<Artist>> {
        return liveData<List<Artist>> {
            val response = artistLocalDBSourceImpl.getAllArtistFromDB()
                .flowOn(Dispatchers.IO).collect {
                    emit(it)
                }
        }
    }


    fun getArtistFromCache(): LiveData<List<Artist>> {
        return artistCacheSourceImpl.getArtistList()
    }
}