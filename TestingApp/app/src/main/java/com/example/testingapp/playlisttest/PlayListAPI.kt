package com.example.testingapp.playlisttest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlayListAPI(val api: API) {

    suspend fun fetchPlayList(): Flow<Result<List<PlayList>>> {
        return flow {
            emit( Result.success(api.fetchAllPlayList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something Went Wrong")))
        }
    }
}