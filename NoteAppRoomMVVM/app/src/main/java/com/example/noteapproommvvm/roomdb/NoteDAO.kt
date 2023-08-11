package com.example.noteapproommvvm.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:NoteEntity)

    @Update
    suspend fun updateNote(note:NoteEntity)

    @Delete
    suspend fun deleteNote(note:NoteEntity)

    @Query("SELECT * FROM notes")
      fun getAllNote(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes where title LIKE :text OR body LIKE :text")
      fun searchNote(  text:String?):LiveData<List<NoteEntity>>
}