package com.example.noteapproommvvm.roomdb

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id:Int=0,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "body")
    val description:String
) :Parcelable