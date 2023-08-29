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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Response

class TVShowRepositoryImpl(
    val tvShowRemoteDataSourceImpl: TVShowRemoteDataSourceImpl,
    val tVShowLocalDBDataSourceImpl: TVShowLocalDBDataSourceImpl,
    val tvShowCacheDataSourceImpl: TVShowCacheDataSourceImpl
) : ITVShowsRepository {

    override suspend fun getPopularTVShows(): List<TVShow> {
        return getTVShowFromCache()
    }

    override suspend fun updateTVShow(tvShowList: List<TVShow>) {
        val newList = getTVShowFromAPI()

        if (newList.size > 0) {

        } else {
            tVShowLocalDBDataSourceImpl.deleteAllTVShowFromDB()
            tVShowLocalDBDataSourceImpl.insertAllTVShowInDB(newList)
            tvShowCacheDataSourceImpl.savePopularTVShowToCache(newList)
        }


    }


    suspend fun getTVShowFromAPI(): List<TVShow> {
        lateinit var tvShowsList: List<TVShow>
        try {
            val response = tvShowRemoteDataSourceImpl.getPopularTVShowFromRemoteSource()
            response.flowOn(Dispatchers.IO).collect {
                tvShowsList = it.results
            }


        } catch (e: Exception) {
            println("TVShowRepositoryImpl ${e.stackTrace}")
        }
        return tvShowsList
    }

    suspend fun getTVShowFromLocalDB(): List<TVShow> {
        lateinit var tvShowsList: List<TVShow>
        try {
            tVShowLocalDBDataSourceImpl.getAllTVShowFromDB().flowOn(Dispatchers.IO).collect {
                if (it.size > 0) {
                    tvShowsList = it
                } else {

                    tvShowsList = getTVShowFromAPI()
                    CoroutineScope(Dispatchers.IO).launch {
                        tVShowLocalDBDataSourceImpl.insertAllTVShowInDB(tvShowsList)
                    }
                }
            }

        } catch (e: Exception) {
            println("TVShowRepositoryImpl ${e.stackTrace}")
        }
        return tvShowsList
    }


    suspend fun getTVShowFromCache(): List<TVShow> {
        lateinit var tvShowsList: List<TVShow>
        try {

            tvShowsList = tvShowCacheDataSourceImpl.getPopularTVShowFromCache()

        } catch (e: Exception) {
            println("TVShowRepositoryImpl ${e.stackTrace}")
        }

        if (tvShowsList.size > 0) {
            return tvShowsList
        } else {
            tvShowsList = getTVShowFromLocalDB()
            tvShowCacheDataSourceImpl.savePopularTVShowToCache(tvShowsList)
        }
        return tvShowsList
    }


}