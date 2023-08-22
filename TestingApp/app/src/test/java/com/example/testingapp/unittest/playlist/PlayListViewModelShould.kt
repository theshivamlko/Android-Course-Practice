package com.example.testingapp.unittest.playlist

import com.example.testingapp.playlisttest.PlayList
import com.example.testingapp.playlisttest.PlayListRepository
import com.example.testingapp.playlisttest.PlayListViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import com.example.testingapp.util.getValueForTest
import junit.framework.Assert.assertNotSame

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayListViewModelShould : BaseUnitTest() {


    private lateinit var playListViewModel: PlayListViewModel

    private val playListRepository: PlayListRepository = mock()
    private val playList: List<PlayList> = mock<List<PlayList>>()


    private val expected = Result.success(playList)
    private val exception =RuntimeException("Something Went Wrong")


    @Test
    fun getPlaylistsFromRepository() {
        playListViewModel = mockSuccessfullCase()

    }

    private fun mockSuccessfullCase(): PlayListViewModel {
        runBlocking {

            whenever(playListRepository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )

        }
        playListViewModel = PlayListViewModel(playListRepository)
        return playListViewModel
    }

    @Test
    fun emitsPlaylistsFromRepository() {
        playListViewModel = mockSuccessfullCase()
        assertEquals(expected, playListViewModel.playList.getValueForTest())
    }


    @Test
    fun emitErrorOnError() {

        runBlocking {
            whenever(playListRepository.getPlayLists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        playListViewModel = PlayListViewModel(playListRepository)
        assertEquals(exception,playListViewModel.playList.getValueForTest()?.exceptionOrNull())

    }


@Test
    fun emitRuntimeErrorOnError() {

        runBlocking {
            whenever(playListRepository.getPlayLists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        playListViewModel = PlayListViewModel(playListRepository)
        assertNotSame( java.lang.RuntimeException("Something"),playListViewModel.playList.getValueForTest()?.exceptionOrNull())

    }
}