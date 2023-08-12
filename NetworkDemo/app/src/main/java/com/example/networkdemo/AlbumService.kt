package com.example.networkdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {


    @GET(value = "/albums")
    suspend fun getAllAlbums(): Response<AlbumsList>


    @GET(value = "/albums")
    suspend fun getAlbum(@Query("id") albumID: Int): Response<AlbumsList>


}