package com.example.librayappcompose.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


// object use for Singleton for class
object ApiService {

    fun provideApi(builder:Retrofit.Builder):BooksApi{
        return builder.build().create(BooksApi::class.java)
    }

    fun provideRetrofit():Retrofit.Builder{
        return   Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
    }


}