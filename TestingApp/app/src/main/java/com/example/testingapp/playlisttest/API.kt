package com.example.testingapp.playlisttest

import retrofit2.http.GET

// Hilt does not know how to create instance of Interface to use anywhere
interface API {
    @GET("playlist")
    suspend fun fetchAllPlayList(): List<PlayList>
}