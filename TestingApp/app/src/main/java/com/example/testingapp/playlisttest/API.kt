package com.example.testingapp.playlisttest

import retrofit2.http.GET

interface API {


    @GET("playlist")
    suspend fun fetchAllPlayList(): List<PlayList>
}