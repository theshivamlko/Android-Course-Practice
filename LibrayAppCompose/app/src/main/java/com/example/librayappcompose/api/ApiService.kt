package com.example.librayappcompose.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


// object use for Singleton for class
object ApiService {


    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    var count = 0;
    val client = OkHttpClient.Builder().apply {

        addInterceptor(interceptor)
        // time period in which app should establish connection,
        // after 30 sec it will stop trying, default 10 sec
        connectTimeout(2, TimeUnit.SECONDS)
        // max timeout b/w arrivals of 2 data packets in waiting for response
        readTimeout(5, TimeUnit.SECONDS)
        // Time gap b/w 2 data packets when sending them 2 server
        writeTimeout(5, TimeUnit.SECONDS)

        interceptors().add(Interceptor { chain ->

            println("Retry ${++count}")

            var request: Request = chain.request()
            request = request.newBuilder()
                .build()
            val response = chain.proceed(request)
            println("Retry response ${response.code}")
            when (response.code) {
                400 -> {
                    //Show Bad Request Error Message
                }

                401 -> {
                    //Show UnauthorizedError Message
                }

                403 -> {
                    //Show Forbidden Message
                }

                404 -> {
                    //Show NotFound Message
                }
                // ... and so on
            }
            return@Interceptor response
        })
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