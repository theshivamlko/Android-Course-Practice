package com.example.networkdemo

import com.google.gson.Gson

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory;
class RetrofitInstance {

    companion object{
        val BASE_URL="https://jsonplaceholder.typicode.com"


        fun getInstance():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}