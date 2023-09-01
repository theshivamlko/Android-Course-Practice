package com.example.unitconvertorcompose.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.unitconvertorcompose.data.roomdb.RoomDBService
import com.example.unitconvertorcompose.domain.repository.ConvertorRepositoryImpl
import com.example.unitconvertorcompose.domain.repository.IConvertorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


//@Module
//@InstallIn(SingletonComponent::class)
class RoomDBModule {

//    @Provides
//    @Singleton
    fun provideConvertorDatabase(context:Context):RoomDBService{
        return Room.databaseBuilder(context, RoomDBService::class.java, "mydb")
            .build()
    }

    /*
    * db will automatically inject from top instance
    *
    * */
//    @Provides
//    @Singleton
    fun provideConvertorRepository(db:RoomDBService):IConvertorRepository{
        return  ConvertorRepositoryImpl(db.convertorDAO())
    }
}