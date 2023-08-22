package com.example.testingapp.playlisttest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayListRepository(val playListAPI: PlayListAPI) {

    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {

        return playListAPI.fetchPlayList()

    }

}
