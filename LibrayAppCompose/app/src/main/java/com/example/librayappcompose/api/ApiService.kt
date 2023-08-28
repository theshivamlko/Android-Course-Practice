package com.example.librayappcompose.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit


// object use for Singleton for class
object ApiService {


    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {

        addInterceptor(interceptor)
        // time period in which app should establish connection,
        // after 30 sec it will stop trying, default 10 sec
        connectTimeout(30000L,TimeUnit.SECONDS)
        // max timeout b/w arrivals of 2 data packets in waiting for response
        readTimeout(30000L,TimeUnit.SECONDS)
        // Time gap b/w 2 data packets when sending them 2 server
        writeTimeout(25000L,TimeUnit.SECONDS)
    }.build()


    fun provideApi(builder: Retrofit.Builder): BooksApi {
        return builder.build().create(BooksApi::class.java)
    }

    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
    }


}