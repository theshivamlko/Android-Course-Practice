package com.example.moviesappretrofitroom.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviesappretrofitroom.R
import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.databinding.ItemListBinding

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var movieList = listOf<MovieModel>()
    fun updateList(movieList: List<MovieModel>) {
        this.movieList = movieList
    }


    inner class MyViewHolder(val itemListBinding: ItemListBinding) :
        ViewHolder(itemListBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflator = LayoutInflater.from(parent.context)

        val binding: ItemListBinding =
            DataBindingUtil.inflate(inflator, R.layout.item_list, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemListBinding.textTitle.text = movieList[position].title
        holder.itemListBinding.textOverview.text = movieList[position].overview
        holder.itemListBinding.textReleaseDate.text = movieList[position].releaseDate


        val url: String = "https://image.tmdb.org/t/p/w200" + movieList[position].posterPath
        println("url ${url}")
        //
        Glide.with(holder.itemListBinding.imageView.context).load(url).into(holder.itemListBinding.imageView)


    }


}