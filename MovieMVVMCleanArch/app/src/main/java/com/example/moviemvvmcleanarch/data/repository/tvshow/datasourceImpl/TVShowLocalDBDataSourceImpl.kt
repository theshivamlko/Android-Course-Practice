package com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl

import com.example.moviemvvmcleanarch.data.model.TVShow
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowLocalDBDataSource
import com.example.moviemvvmcleanarch.data.roomdb.TVShowDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TVShowLocalDBDataSourceImpl(private val tvShowDAO: TVShowDAO) : ITVShowLocalDBDataSource {




    override suspend fun getAllTVShowFromDB(): Flow<List<TVShow>> {
        return flow {
            tvShowDAO.getAllTVShows()
        }
    }

    override suspend fun insertAllTVShowInDB(tvshows:   List<TVShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDAO.insertAllTVShows(tvshows)
        }
    }

    override suspend fun deleteAllTVShowFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDAO.deleteAllTVShows()
        }
    }


}