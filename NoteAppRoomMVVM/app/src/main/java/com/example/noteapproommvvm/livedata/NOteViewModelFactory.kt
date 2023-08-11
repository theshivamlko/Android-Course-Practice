package com.example.noteapproommvvm.livedata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapproommvvm.NoteRepositories
import com.example.noteapproommvvm.roomdb.NoteDAO
import java.lang.IllegalArgumentException

class NOteViewModelFactory(val app: Application,val noteRepositories: NoteRepositories):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){

            return NoteViewModel(app,noteRepositories) as T
        }

        throw  IllegalArgumentException("Unknown ViewModel")
    }


}