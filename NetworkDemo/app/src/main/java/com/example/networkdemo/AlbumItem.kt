package com.example.networkdemo

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    var userId: Int ,
    @SerializedName("id") var albumID: Int ,
    @SerializedName("title") var albumName: String
)