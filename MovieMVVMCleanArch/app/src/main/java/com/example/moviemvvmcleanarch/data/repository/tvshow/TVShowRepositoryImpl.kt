package com.example.moviemvvmcleanarch.data.repository.movie

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.data.model.TVShowsList
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowRemoteDataSource
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
import kotlinx.coroutines.withContext
import retrofit2.Response

class TVShowRepositoryImpl(
    val itvShowRemoteDataSource: ITVShowRemoteDataSource,
    val itvShowLocalDBDataSource: ITVShowLocalDBDataSource,
    val itvShowCacheDataSource: ITVShowCacheDataSource
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
                    itvShowLocalDBDataSource.deleteAllTVShowFromDB()
                    itvShowLocalDBDataSource.insertAllTVShowInDB(it)
                    itvShowCacheDataSource.savePopularTVShowToCache(it)
                    emit(it)
                }


            }


        }


    }


    suspend fun getTVShowFromAPI(): Flow<List<TVShow>> {
        return flow {
            try {
                val response = itvShowRemoteDataSource.getPopularTVShowFromRemoteSource()
                val results = response.body()
                if (results != null) {
                    emit(results.results)
                }
            } catch (e: Exception) {
                println("TVShowRepositoryImpl 2 getTVShowFromAPI ${e.localizedMessage}")
                e.printStackTrace()
            }

        }

    }

    suspend fun getTVShowFromLocalDB(): Flow<List<TVShow>> {
        println("TVShowRepositoryImpl 1 getTVShowFromLocalDB  ")

        return flow {
            println("TVShowRepositoryImpl 2 getTVShowFromLocalDB  ")
            itvShowLocalDBDataSource.getAllTVShowFromDB().flowOn(Dispatchers.IO).collect {
                println("TVShowRepositoryImpl 3 getTVShowFromLocalDB $it")
                if (it.size > 0) {
                    emit(it)
                } else {

                    getTVShowFromAPI().collect {
                        emit(it)
                        CoroutineScope(Dispatchers.IO).launch {
                            itvShowLocalDBDataSource.insertAllTVShowInDB(it)
                        }
                    }

                }
            }

        }
    }


    suspend fun getTVShowFromCache(): Flow<List<TVShow>> {
        println("TVShowRepositoryImpl 1  getTVShowFromCache")

        return flow {
            try {
                val list = itvShowCacheDataSource.getPopularTVShowFromCache()
                println("TVShowRepositoryImpl 2 getTVShowFromCache $list")
                if (list.isNotEmpty()) {
                    emit(list)
                } else {
                    getTVShowFromLocalDB().collect {
                        itvShowCacheDataSource.savePopularTVShowToCache(it)
                        emit(list)
                    }
                }

            } catch (e: Exception) {
                println("TVShowRepositoryImpl error ${e.localizedMessage}")
                e.printStackTrace()
            }


        }
    }


}