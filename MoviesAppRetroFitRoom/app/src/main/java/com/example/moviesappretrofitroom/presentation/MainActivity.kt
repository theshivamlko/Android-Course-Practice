package com.example.moviesappretrofitroom.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesappretrofitroom.App
import com.example.moviesappretrofitroom.R
import com.example.moviesappretrofitroom.data.MovieRepositoriesImpl
import com.example.moviesappretrofitroom.databinding.ActivityMainBinding
import com.example.moviesappretrofitroom.presentation.dependencies.Injector
import com.example.moviesappretrofitroom.presentation.livedata.MovieViewModel
import com.example.moviesappretrofitroom.presentation.livedata.MovieViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, (R.layout.activity_main))

        (applicationContext as Injector).createMovieSubComponent().inject(this)

        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        activityMainBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = MovieAdapter()
        activityMainBinding.recyclerView.adapter = adapter

        activityMainBinding.progressBar.visibility = View.VISIBLE

        val response=movieViewModel.getMovies()

        response.observe(this){

            println("response observe ${it}")
            if(it!=null){
                adapter.updateList(it)
                adapter.notifyDataSetChanged()
                activityMainBinding.progressBar.visibility=View.GONE
            }
            else{
                Toast.makeText(this,"No Data Available",Toast.LENGTH_SHORT).show()
            }
        }


    }
}