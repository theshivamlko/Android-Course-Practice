package com.example.moviemvvmcleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.databinding.ActivityMainBinding
import com.example.moviemvvmcleanarch.presentation.artist.ArtistFragment
import com.example.moviemvvmcleanarch.presentation.di.Injector
import com.example.moviemvvmcleanarch.presentation.movie.MovieFragment
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModel
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModelFactory
import com.example.moviemvvmcleanarch.presentation.tvshow.TvShowFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


//    @Inject
//    lateinit var movieViewModelFactory: MovieViewModelFactory

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       activityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)


        activityMainBinding.button.setOnClickListener {
           val fragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,MovieFragment())

        }
        activityMainBinding.button2.setOnClickListener {
           val fragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,TvShowFragment())

        }
        activityMainBinding.button3.setOnClickListener {
           val fragmentTransaction= supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,ArtistFragment())

        }



    //   (application as Injector).createMovieSubComponent().inject(this)
//        (application as Injector).createTVShowSubComponent().inject(this)
//        (application as Injector).createMovieSubComponent().inject(this)


       /* movieViewModel=ViewModelProvider(this,movieViewModelFactory)
            .get(MovieViewModel::class.java)*/

       /* movieViewModel.getMovies().observe(this){
            println("MainActivity $it")
            println("MainActivity ${it.size}")
        }*/


    }
}