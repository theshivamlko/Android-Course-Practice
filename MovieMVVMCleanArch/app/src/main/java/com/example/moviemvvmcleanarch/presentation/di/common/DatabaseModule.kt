package com.example.moviemvvmcleanarch.presentation.di.common

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviemvvmcleanarch.data.roomdb.ArtistDAO
import com.example.moviemvvmcleanarch.data.roomdb.MovieDAO
import com.example.moviemvvmcleanarch.data.roomdb.TMDBRoomDB
import com.example.moviemvvmcleanarch.data.roomdb.TVShowDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context): RoomDatabase {
        return Room.databaseBuilder(context, TMDBRoomDB::class.java, "mydb").build()
    }

    @Singleton
    @Provides
    fun getMovieDao(tmdbRoomDB: TMDBRoomDB):MovieDAO{
        return tmdbRoomDB.movieDAO()
    }
    @Singleton
    @Provides
    fun getTVShowDao(tmdbRoomDB: TMDBRoomDB):TVShowDAO{
        return tmdbRoomDB.tvShowDAO()
    }
    @Singleton
    @Provides
    fun getArtistDao(tmdbRoomDB: TMDBRoomDB):ArtistDAO{
        return tmdbRoomDB.artistDAO()
    }

}