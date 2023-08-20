package com.example.librayappcompose.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.librayappcompose.roomdb.BookEntity

interface IAppData {


    suspend fun getAllBooks(): LiveData<List<BookEntity>>


    suspend fun insertBook(bookEntity: BookEntity)

    suspend fun updateBook(bookEntity: BookEntity)

    suspend fun deleteBook(bookEntity: BookEntity)

}