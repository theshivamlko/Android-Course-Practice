package com.example.moviemvvmcleanarch.data.repository.movie

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.data.model.TVShowsList
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Response

class TVShowRepositoryImpl(
    val tvShowRemoteDataSourceImpl: TVShowRemoteDataSourceImpl,
    val tVShowLocalDBDataSourceImpl: TVShowLocalDBDataSourceImpl,
    val tvShowCacheDataSourceImpl: TVShowCacheDataSourceImpl
) : ITVShowsRepository {

    override suspend fun getPopularTVShows(): Flow<List<TVShow>> {
        return getTVShowFromCache()
    }


    override suspend fun refreshPopularTvShows(): Flow<List<TVShow>> {

        return flow {
            getTVShowFromAPI().collect {
                if (it.size > 0) {

                    emit(it)
                } else {
                    tVShowLocalDBDataSourceImpl.deleteAllTVShowFromDB()
                    tVShowLocalDBDataSourceImpl.insertAllTVShowInDB(it)
                    tvShowCacheDataSourceImpl.savePopularTVShowToCache(it)
                    emit(it)
                }


            }


        }


    }


    suspend fun getTVShowFromAPI(): Flow<List<TVShow>> {
        return flow {
            try {
                val response = tvShowRemoteDataSourceImpl.getPopularTVShowFromRemoteSource()
                response.flowOn(Dispatchers.IO).collect {
                    emit(it.results)
                }
            } catch (e: Exception) {
                println("TVShowRepositoryImpl ${e.stackTrace}")
            }

        }

    }

    suspend fun getTVShowFromLocalDB(): Flow<List<TVShow>> {

        return flow {
            tVShowLocalDBDataSourceImpl.getAllTVShowFromDB().flowOn(Dispatchers.IO).collect {
                if (it.size > 0) {
                    emit(it)
                } else {

                    getTVShowFromAPI().collect {
                        CoroutineScope(Dispatchers.IO).launch {
                            tVShowLocalDBDataSourceImpl.insertAllTVShowInDB(it)
                            emit(it)
                        }
                    }

                }
            }

        }
    }


    suspend fun getTVShowFromCache(): Flow<List<TVShow>> {

        return flow {
            try {
                val list = tvShowCacheDataSourceImpl.getPopularTVShowFromCache()
                if (list.isNotEmpty()) {
                    emit(list)
                } else {
                    getTVShowFromLocalDB().collect {
                        tvShowCacheDataSourceImpl.savePopularTVShowToCache(it)
                        emit(list)
                    }
                }

            } catch (e: Exception) {
                println("TVShowRepositoryImpl ${e.stackTrace}")
            }


        }
    }


}