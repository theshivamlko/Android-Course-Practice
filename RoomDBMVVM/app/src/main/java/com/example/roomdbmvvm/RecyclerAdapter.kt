package com.example.roomdbmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbmvvm.databinding.ItemListBinding
import com.example.roomdbmvvm.roomdb.User

class RecyclerAdapter(val userList:List<User>,private val clickListener: (User) ->Unit): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)

        val itemListBinding:ItemListBinding=DataBindingUtil.inflate(layoutInflater,R.layout.item_list,parent,false)

        return MyViewHolder(itemListBinding)
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.bind(userList[position],clickListener)

    }


}

class MyViewHolder(val binding:ItemListBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(user:User,clickListener: (User) -> Unit){
        binding.textView.text="${user.name} \n ${user.email}"

        binding.imageView.setOnClickListener {
            clickListener(user)
        }

    }


}