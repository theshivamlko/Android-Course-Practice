package com.example.moviemvvmcleanarch.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.databinding.ItemListBinding

class MovieAdapter() : RecyclerView.Adapter<MyViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun refreshList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)
        val holder = MyViewHolder(binding)

        return holder

    }

    override fun getItemCount(): Int {
        return  movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie=movieList.get(position)
        holder.bind(movie)

        holder.itemListBinding.imageView.setOnClickListener {
            Toast.makeText(holder.itemListBinding.root.context,"Clicked \n ${movie.title}",Toast.LENGTH_SHORT).show()
        }
    }


}


class MyViewHolder(val itemListBinding: ItemListBinding) :
    RecyclerView.ViewHolder(itemListBinding.root) {

    fun bind(movie: Movie) {
        itemListBinding.model=movie
   //     itemListBinding.textView.text = movie.title
// https://image.tmdb.org/t/p/w300/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg
        Glide.with(itemListBinding.imageView.context)
            .load("https://image.tmdb.org/t/p/w300/${movie.posterPath}")
            .into(itemListBinding.imageView)

    }
}