package com.example.testingapp.playlisttest

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayListRepository @Inject constructor(val playListAPI: PlayListAPI) {

    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        return playListAPI.fetchPlayList()
    }

}
