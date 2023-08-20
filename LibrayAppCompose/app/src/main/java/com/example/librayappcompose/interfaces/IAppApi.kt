package com.example.librayappcompose.interfaces

import com.example.librayappcompose.roomdb.BookEntity

interface IAppApi {

    suspend fun getAllBooks(): List<BookEntity>

}