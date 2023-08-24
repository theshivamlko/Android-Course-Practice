package com.example.androidarchitecture.model

import com.google.gson.GsonBuilder
import io.reactivex.Observable

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CountryService {

    lateinit var retrofit: Retrofit

    lateinit var countryAPI: CountryAPI

    init {

        val client=OkHttpClient()

        val gson = GsonBuilder()
            .registerTypeAdapter(Country::class.java, MyDeserializer())
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .client(client)
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        countryAPI = retrofit.create(CountryAPI::class.java)
    }

      fun getCountries(): Observable<List<Country>> {

        return countryAPI.getAllCountries()

    }
}