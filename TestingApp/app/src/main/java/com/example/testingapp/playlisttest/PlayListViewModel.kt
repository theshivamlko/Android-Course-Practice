package com.example.testingapp.playlisttest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PlayListViewModel(val playListRepository:PlayListRepository):ViewModel() {

    val playList =MutableLiveData<Result<List<PlayList>>>()

    init {
        viewModelScope.launch {
            playListRepository.getPlayLists()
                .collect{
                    playList.value=it
                }
        }
    }
}