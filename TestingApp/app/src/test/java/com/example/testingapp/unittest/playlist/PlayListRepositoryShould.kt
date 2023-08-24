package com.example.testingapp.unittest.playlist

import com.example.testingapp.playlisttest.PlayListMapper
import com.example.testingapp.playlisttest.PlayList
import com.example.testingapp.playlisttest.PlayListAPI
import com.example.testingapp.playlisttest.PlayListRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlayListRepositoryShould : BaseUnitTest() {


    val playListMapper: PlayListMapper = mock<PlayListMapper>()
    val playList = mock<List<PlayList>>()
    val playListAPI: PlayListAPI = mock()
    val exception=RuntimeException("Something Went Wrong")

    @Test
    fun getPlayListFromAPI() {

        runBlocking {
            val playListRepository: PlayListRepository = mockSuccessFullTest()
            playListRepository.getPlayLists()
            verify(playListAPI, times(1)).fetchPlayList()
        }

    }

    @Test
    fun emitPlaylistFromAPI() {

        runBlocking {

            val playListRepository: PlayListRepository = mockSuccessFullTest()



            assertEquals(playList, playListRepository.getPlayLists().first().getOrNull())
        }


    }

    @Test
    fun propagateErrors(){

        runBlocking {
            whenever(playListAPI.fetchPlayList()).thenReturn(
                flow {
                    emit(Result.failure<List<PlayList>>(exception))
                }
            )

            val playListRepository=PlayListRepository(playListAPI)

        //    assertEquals(exception,playListRepository.getPlayLists().first().exceptionOrNull())
       //     assertEquals(java.lang.RuntimeException("Hello"),playListRepository.getPlayLists().first().exceptionOrNull())
              assertEquals(java.lang.RuntimeException("Something Went Wrong"),playListRepository.getPlayLists().first().exceptionOrNull())
        }

    }
    @Test
    fun delegateBussinessLogicToMapper(){

        runBlocking {
            val playListRepository=PlayListRepository(playListAPI)
            playListRepository.getPlayLists().first()

            verify(mapper,times(1)).invoke(playList)
        }

    }

    private suspend fun mockSuccessFullTest(): PlayListRepository {
        whenever(playListAPI.fetchPlayList()).thenReturn(
            flow {
                emit(Result.success(playList))
            }
        )

        return PlayListRepository(playListAPI)

    }

}