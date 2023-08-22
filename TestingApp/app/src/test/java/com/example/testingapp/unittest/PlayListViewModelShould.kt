package com.example.testingapp.unittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.example.testingapp.playlisttest.PlayList
import com.example.testingapp.playlisttest.PlayListRepository
import com.example.testingapp.playlisttest.PlayListViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

import org.junit.Rule
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.utils.getValueForTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayListViewModelShould {

    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var playListViewModel: PlayListViewModel

    private val playListRepository: PlayListRepository = mock()
    private val playList: List<PlayList> = mock<List<PlayList>>()


    private val expected = Result.success(playList)


    @Test
    fun getPlaylistsFromRepository() {
        runBlocking {

            whenever(playListRepository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )

            playListViewModel = PlayListViewModel(playListRepository)
        }
        runBlocking {
            playListViewModel.playList.getValueForTest()

            // check if this function invoked before or not
            verify(playListRepository, times(1)).getPlayLists()

        }

    }

    @Test
    fun emitsPlaylistsFromRepository() {

        runBlocking {
            whenever(playListRepository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )


        }
        playListViewModel = PlayListViewModel(playListRepository)
        assertEquals(expected, playListViewModel.playList.getValueForTest())


    }
}