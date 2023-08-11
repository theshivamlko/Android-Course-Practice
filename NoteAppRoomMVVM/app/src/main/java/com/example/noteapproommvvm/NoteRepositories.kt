package com.example.noteapproommvvm

import androidx.lifecycle.LiveData
import com.example.noteapproommvvm.roomdb.NoteEntity
import com.example.noteapproommvvm.roomdb.NoteRoomDatabase

class NoteRepositories(val noteRoomDatabase:   NoteRoomDatabase) {


    suspend fun insertNote(note: NoteEntity) {

        noteRoomDatabase.getNoteDao().insertNote(note)

    }

    suspend fun updateNote(note: NoteEntity) {

        noteRoomDatabase.getNoteDao().updateNote(note)

    }

    suspend fun deleteNote(note: NoteEntity) {

        noteRoomDatabase.getNoteDao().deleteNote(note)

    }

    fun getAllNote(): LiveData<List<NoteEntity>> {
        return noteRoomDatabase.getNoteDao().getAllNote()
    }

    fun searchNote(text: String?): LiveData<List<NoteEntity>> {
        return noteRoomDatabase.getNoteDao().searchNote(text)
    }


}