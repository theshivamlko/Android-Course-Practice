package com.example.testingapp.unittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.example.testingapp.playlisttest.PlayListRepository
import com.example.testingapp.playlisttest.PlayListViewModel
import org.junit.Test

import org.junit.Rule
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import petros.efthymiou.groovy.utils.getValueForTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayListViewModelShould {

    @get:Rule
    val coroutineTestRule=MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()

    val playListViewModel:PlayListViewModel

    val playListRepository: PlayListRepository = mock()



    init {
        playListViewModel=PlayListViewModel(playListRepository)
    }

    @Test
    fun getPlaylistsFromRepository() {

        playListViewModel.playList.getValueForTest()

        // check if this function invoked before or not
        verify(playListRepository,times(1)).getPlayLists()
    }
}