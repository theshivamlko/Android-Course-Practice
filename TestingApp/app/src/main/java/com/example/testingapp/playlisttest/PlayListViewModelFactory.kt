package com.example.testingapp.playlisttest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlayListViewModelFactory @Inject constructor(val playListRepository: PlayListRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel(playListRepository) as T
    }

}
