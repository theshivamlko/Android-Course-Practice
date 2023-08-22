package com.example.testingapp.playlisttest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class PlayListViewModel(val playListRepository:PlayListRepository):ViewModel() {

    val playList =MutableLiveData<List<PlayList>>()

    init {
        playListRepository.getPlayLists()
    }
}