package com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl

 import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowCacheDataSource


//@Singleton
class TVShowCacheDataSourceImpl  : ITVShowCacheDataSource {

    private val tvshowList = mutableListOf<TVShow>()


    override suspend fun getPopularTVShowFromCache(): List<TVShow> {
        return  tvshowList
    }

    override suspend fun savePopularTVShowToCache(list: List<TVShow>) {
        tvshowList.clear()
        tvshowList.addAll(list)    }


}