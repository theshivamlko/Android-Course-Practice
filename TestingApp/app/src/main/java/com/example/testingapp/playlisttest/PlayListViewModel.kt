package com.example.testingapp.playlisttest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class PlayListViewModel @Inject constructor(val playListRepository:PlayListRepository):ViewModel() {

  //  val playList =MutableLiveData<Result<List<PlayList>>>()
  val loader =MutableLiveData<Boolean>()

    // Other Version
    val playList =liveData<Result<List<PlayList>>>(){

        loader.postValue(true)

        println("liveData")
        emitSource(playListRepository.getPlayLists().onEach {
            loader.postValue(false)


        }.asLiveData())
  }




    init {


        // Alternate
       /* viewModelScope.launch {
            playListRepository.getPlayLists()
                .collect{
                    playList.value=it
                }
        }*/
    }
}