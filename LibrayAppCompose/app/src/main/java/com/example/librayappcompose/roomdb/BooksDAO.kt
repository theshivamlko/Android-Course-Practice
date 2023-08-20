package com.example.librayappcompose.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface BooksDAO {

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>


    @Insert
    suspend fun insertBook(bookEntity: BookEntity)

    @Update
    suspend fun updateBook(bookEntity: BookEntity)

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)
}