package com.example.librayappcompose.api

import com.example.librayappcompose.interfaces.IAppApi
import com.example.librayappcompose.roomdb.BookEntity

class ApiRepository(private val booksApi: BooksApi) : IAppApi {
    override suspend fun getAllBooks(): List<BookEntity> {
        return booksApi.getAllBooks()
    }


}