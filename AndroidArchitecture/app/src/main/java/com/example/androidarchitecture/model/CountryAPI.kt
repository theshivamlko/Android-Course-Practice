package com.example.androidarchitecture.model

    import io.reactivex.Observable
    import retrofit2.http.GET

interface CountryAPI {

    @GET("all")
    fun getAllCountries(): Observable<List<Country>>
}