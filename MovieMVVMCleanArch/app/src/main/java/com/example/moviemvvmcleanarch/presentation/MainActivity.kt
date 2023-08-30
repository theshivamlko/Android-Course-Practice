package com.example.moviemvvmcleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.moviemvvmcleanarch.BuildConfig
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.roomdb.TMDBRoomDB
import com.example.moviemvvmcleanarch.databinding.ActivityMainBinding
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.presentation.artist.ArtistFragment
import com.example.moviemvvmcleanarch.presentation.movie.MovieFragment
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModel
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModelFactory
import com.example.moviemvvmcleanarch.presentation.tvshow.TvShowFragment
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    //  @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        activityMainBinding.button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, MovieFragment())

        }
        activityMainBinding.button2.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, TvShowFragment())

        }
        activityMainBinding.button3.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, ArtistFragment())

        }


        //     (application as Injector).createMovieSubComponent().inject(this)
//        (application as Injector).createTVShowSubComponent().inject(this)
//        (application as Injector).createMovieSubComponent().inject(this)


        initMovie()

        /* movieViewModel.getMovies().observe(this){
             println("MainActivity $it")
             println("MainActivity ${it.size}")
         }*/


    }


    fun initMovie() {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {

            addInterceptor(interceptor)
            // time period in which app should establish connection,
            // after 30 sec it will stop trying, default 10 sec
            connectTimeout(2, TimeUnit.SECONDS)
            // max timeout b/w arrivals of 2 data packets in waiting for response
            readTimeout(5, TimeUnit.SECONDS)
            // Time gap b/w 2 data packets when sending them 2 server
            writeTimeout(5, TimeUnit.SECONDS)

            interceptors().add(Interceptor { chain ->


                var request: Request = chain.request()
                request = request.newBuilder()
                    .build()
                val response = chain.proceed(request)
                println("Retry response ${response.code}")
                when (response.code) {
                    400 -> {
                        //Show Bad Request Error Message
                    }

                    401 -> {
                        //Show UnauthorizedError Message
                    }

                    403 -> {
                        //Show Forbidden Message
                    }

                    404 -> {
                        //Show NotFound Message
                    }
                    // ... and so on
                }
                return@Interceptor response
            })
        }.build()

        val tmdbService: TMDBService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build().create(TMDBService::class.java)


        val roomDatabase = Room.databaseBuilder(this, TMDBRoomDB::class.java, "mydb")
            .build()

        val iMovieRemoteDataSource: IMovieRemoteDataSource = MovieRemoteDataSourceImpl(tmdbService)
        val iMovieLocalDBDataSource: IMovieLocalDBDataSource =
            MovieLocalDBDataSourceImpl(roomDatabase.movieDAO())
        val iMovieCacheDataSource: IMovieCacheDataSource = MovieCacheDataSourceImpl()


        val movieRepository: IMovieRepository = MovieRepositoryImpl(
            iMovieRemoteDataSource,
            iMovieLocalDBDataSource,
            iMovieCacheDataSource
        )
        val getMoviesUseCase = GetMoviesUseCase(movieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(movieRepository)

        movieViewModelFactory = MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)


        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)

        movieViewModel.getMovies().observe(this){
            println("MainActivity getMovies $it")
        }
    }
}