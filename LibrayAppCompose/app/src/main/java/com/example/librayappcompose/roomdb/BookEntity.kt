package com.example.librayappcompose.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.jetbrains.annotations.Nullable


@JsonClass(generateAdapter = true)
@Entity(tableName = "books",)
data class BookEntity(

    @Nullable
    @Json(name = "yearOfBirth")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val bookId:Int?,


    @Json(name = "name")
    val bookName:String

)