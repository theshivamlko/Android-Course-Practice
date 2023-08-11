package com.example.noteapproommvvm.livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapproommvvm.NoteRepositories
import com.example.noteapproommvvm.roomdb.NoteEntity
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, val noteRepositories: NoteRepositories) :
    AndroidViewModel(app) {


    fun addNote(note: NoteEntity) = viewModelScope.launch {
        noteRepositories.insertNote(note)
    }

    fun deleteNote(note: NoteEntity) = viewModelScope.launch {
        noteRepositories.deleteNote(note)
    }

    fun updateNote(note: NoteEntity) = viewModelScope.launch {
        noteRepositories.updateNote(note)
    }

    fun getAllNote() = noteRepositories.getAllNote()


fun searchNote(text: String)= noteRepositories.searchNote(text)






}