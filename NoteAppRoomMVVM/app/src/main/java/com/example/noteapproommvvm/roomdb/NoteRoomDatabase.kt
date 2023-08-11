package com.example.noteapproommvvm.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDAO

    companion object {
        @Volatile
         var INSTANCE: NoteRoomDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context):NoteRoomDatabase {
            synchronized(LOCK) {
                INSTANCE =
                    Room.databaseBuilder(context, NoteRoomDatabase::class.java, "mydb").build()
            }
            return INSTANCE!!
        }


    }


}