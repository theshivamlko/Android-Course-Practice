package com.example.moviemvvmcleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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




    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        activityMainBinding.button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, MovieFragment()).commit()

        }
        activityMainBinding.button2.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, TvShowFragment()).commit()

        }
        activityMainBinding.button3.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, ArtistFragment()).commit()

        }


        //     (application as Injector).createMovieSubComponent().inject(this)
//        (application as Injector).createTVShowSubComponent().inject(this)
//        (application as Injector).createMovieSubComponent().inject(this)




        /* movieViewModel.getMovies().observe(this){
             println("MainActivity $it")
             println("MainActivity ${it.size}")
         }*/


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflator:MenuInflater=menuInflater
         inflator.inflate(R.menu.menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.refresh -> supportFragmentManager.fragments.forEach {
                if(it is MovieFragment){
                    it.updateMovie()
                }
            }
        }

        return true
    }


}