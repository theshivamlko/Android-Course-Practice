package com.example.unitconvertorcompose.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unitconvertorcompose.data.model.Conversion
import com.example.unitconvertorcompose.data.model.ConversionResult


@Database(entities = [ConversionResult::class], version = 1)
abstract class RoomDBService : RoomDatabase() {


    abstract fun convertorDAO(): ConvertorDAO


    companion object {
        fun getInstance(context: Context): RoomDBService {
            return Room.databaseBuilder(context, RoomDBService::class.java, "mydb")
                .build()
        }
    }

}