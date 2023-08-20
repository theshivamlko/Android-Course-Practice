package com.example.librayappcompose.api

import com.example.librayappcompose.roomdb.BookEntity
import retrofit2.http.GET

interface BooksApi {

    @GET("characters")
    suspend fun getAllBooks(): List<BookEntity>
}