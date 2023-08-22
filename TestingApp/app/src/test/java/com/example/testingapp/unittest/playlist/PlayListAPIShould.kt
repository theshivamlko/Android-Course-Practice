package com.example.testingapp.unittest.playlist

import com.example.testingapp.playlisttest.API
import com.example.testingapp.playlisttest.PlayList
import com.example.testingapp.playlisttest.PlayListAPI
import com.example.testingapp.playlisttest.PlayListRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlayListAPIShould : BaseUnitTest() {


    val api: API = mock()
    lateinit var playListAPI: PlayListAPI
    val playList: List<PlayList> = mock()

    val exception=RuntimeException("JSON issue")

    @Test
    fun responseReceived() {

        runBlocking {

            playListAPI = PlayListAPI(api)
            // Call API, call first() to invoke
            playListAPI.fetchPlayList().first()
            // Check API function with URL was called
            verify(api, times(1)).fetchAllPlayList()
        }

    }


    @Test
    fun convertResultToFlow() {

        runBlocking {


            whenever(api.fetchAllPlayList()).thenReturn(playList)

            playListAPI = PlayListAPI(api)

            assertEquals(Result.success(playList), playListAPI.fetchPlayList().first())

        }
    }
    @Test
    fun playListServiceFailed() {

        runBlocking {
            whenever(api.fetchAllPlayList()).thenThrow(RuntimeException("API issue"))
            playListAPI = PlayListAPI(api)
            assertEquals(RuntimeException("Something Went Wrong").message, playListAPI.fetchPlayList().first().exceptionOrNull()!!.message)

        }
    }


}