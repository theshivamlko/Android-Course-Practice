package com.example.testingapp.playlisttest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListAPI @Inject constructor(val api: API) {

    suspend fun fetchPlayList(): Flow<Result<List<PlayList>>> {
        println("fetchPlayList1 ${api.fetchAllPlayList()}")
        return flow {
            println("fetchPlayList2 ")
            emit( Result.success(api.fetchAllPlayList()))
        }.catch {
            println("fetchPlayList1 Errir ${it}")
            emit(Result.failure(RuntimeException("Something Went Wrong")))
        }
    }
}