package com.example.moviemvvmcleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.databinding.ActivityMainBinding
import com.example.moviemvvmcleanarch.presentation.artist.ArtistFragment
import com.example.moviemvvmcleanarch.presentation.movie.MovieFragment
import com.example.moviemvvmcleanarch.presentation.tvshow.TvShowFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       activityMainBinding=DataBindingUtil. setContentView(this, R.layout.activity_main)

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


    }
}