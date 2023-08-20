package com.example.librayappcompose.interfaces

import com.example.librayappcompose.roomdb.BookEntity
import kotlinx.coroutines.flow.Flow

interface IAppRoomDB {


      fun getAllBooks(): Flow<List<BookEntity>>


    suspend fun insertBook(bookEntity: BookEntity)

    suspend fun updateBook(bookEntity: BookEntity)

    suspend fun deleteBook(bookEntity: BookEntity)

}