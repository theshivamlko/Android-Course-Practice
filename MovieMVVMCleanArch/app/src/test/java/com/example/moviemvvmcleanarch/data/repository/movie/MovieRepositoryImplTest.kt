package com.example.moviemvvmcleanarch.data.repository.movie

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.moviemvvmcleanarch.BuildConfig
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.roomdb.TMDBRoomDB
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepositoryImplTest {


 lateinit var instrumentationContext: Context

    lateinit var movieRepository: IMovieRepository





    @Before
    fun setUp() {
      //  instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        val context:Context=mock()

        val roomDatabase = Room.databaseBuilder(context, TMDBRoomDB::class.java, "mydb")
            .build()

        val tmdbService: TMDBService = Retrofit.Builder()
            //  .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build().create(TMDBService::class.java)


        val iMovieRemoteDataSource: IMovieRemoteDataSource = MovieRemoteDataSourceImpl(tmdbService)
        val iMovieLocalDBDataSource: IMovieLocalDBDataSource =
            MovieLocalDBDataSourceImpl(roomDatabase.movieDAO())
        val iMovieCacheDataSource: IMovieCacheDataSource = MovieCacheDataSourceImpl()


        movieRepository = MovieRepositoryImpl(
            iMovieRemoteDataSource,
            iMovieLocalDBDataSource,
            iMovieCacheDataSource
        )
        val getMoviesUseCase = GetMoviesUseCase(movieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(movieRepository)


    }

    @After
    fun tearDown() {
    }
/*
    @Test
    fun getPopularMovies() {
    }

    @Test
    fun refreshPopularMovies() {
    }*/

    @Test
    fun getMoviesFromAPI() {

        runBlocking {

                val list= movieRepository.getPopularMovies()
                println("getMoviesFromAPI $list")

                assertTrue(list.isEmpty())

        }




    }

   /* @Test
    fun getMoviesFromLocalDB() {
    }

    @Test
    fun getMoviesFromCache() {
    }

    @Test
    fun getIMovieRemoteDataSource() {
    }

    @Test
    fun getIMovieLocalDBDataSource() {
    }

    @Test
    fun getIMovieCacheDataSource() {
    }*/
}