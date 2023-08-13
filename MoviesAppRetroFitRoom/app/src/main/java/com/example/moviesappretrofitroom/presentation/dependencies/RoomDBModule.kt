package com.example.moviesappretrofitroom.presentation.dependencies

import android.content.Context
import androidx.room.Room
import com.example.moviesappretrofitroom.data.api.TMDBService
import com.example.moviesappretrofitroom.data.roomdb.AppRoomDB
import com.example.moviesappretrofitroom.data.roomdb.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDBModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): AppRoomDB {
        return  Room.databaseBuilder(context,AppRoomDB::class.java,"movidedb").build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(appRoomDB: AppRoomDB):MovieDao{
        return  appRoomDB.getMovieDao()
    }

}