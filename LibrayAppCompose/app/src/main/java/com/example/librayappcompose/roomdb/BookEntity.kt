package com.example.librayappcompose.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books",)
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val bookId:Int,


    val bookName:String

)