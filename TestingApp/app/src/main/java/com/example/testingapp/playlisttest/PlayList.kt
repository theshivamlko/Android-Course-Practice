package com.example.testingapp.playlisttest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PlayList(
    @Json(name = "id")
    val  id:String,
    @Json(name = "name")
    val name:String,
    @Json(name = "category")
    val category:String)
