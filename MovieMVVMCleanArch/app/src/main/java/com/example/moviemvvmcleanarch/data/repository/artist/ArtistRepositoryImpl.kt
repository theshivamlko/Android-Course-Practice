package com.example.moviemvvmcleanarch.data.repository.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistCacheSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistLocalDBSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistCacheSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistLocalDBSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.domain.repository.IArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtistRepositoryImpl(
    val iArtistRemoteDataSource: IArtistRemoteDataSource,
    val iArtistLocalDBSource: IArtistLocalDBSource,
    val iArtistCacheSource: IArtistCacheSource
) : IArtistRepository {

    override suspend fun getPopularArtist(): Flow<List<Artist>> {
        return flow<List<Artist>> {

            var list = getArtistFromCache().value
            if (list != null) {
                emit(list!!)
            } else {
                list = getArtistFromLocalDB().value
                if (list != null) {
                    emit(list!!)
                } else {
                    list = getArtistsFromAPI().value
                    if (list != null) {
                        iArtistLocalDBSource.deleteAllArtistFromDB()
                        iArtistLocalDBSource.saveArtistListToDB(list)
                        iArtistCacheSource.savePopularArtistToCache(list)
                        emit(list!!)
                    }
                }

            }

        }

    }

    override suspend fun refreshPopularArtists(): Flow<List<Artist>> {
        return getPopularArtist()
    }


    fun getArtistsFromAPI(): LiveData<List<Artist>> {

        return liveData<List<Artist>> {
            val response = iArtistRemoteDataSource.getPopularArtistFromRemoteSource()
                .flowOn(Dispatchers.IO).collect {
                    emit(it.results)
                }
        }


    }

    fun getArtistFromLocalDB(): LiveData<List<Artist>> {
        return liveData<List<Artist>> {
            val response = iArtistLocalDBSource.getAllArtistFromDB()
                .flowOn(Dispatchers.IO).collect {
                    emit(it)
                }
        }
    }


    fun getArtistFromCache(): LiveData<List<Artist>> {
        return iArtistCacheSource.getArtistList()
    }
}