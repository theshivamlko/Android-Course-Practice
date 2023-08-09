package com.example.listviewapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdpater(val list: ArrayList<Country>) :
    RecyclerView.Adapter<MyRecyclerViewAdpater.MyViewHolder>() {

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var textView: TextView? = null
        init {
            textView = item.findViewById<TextView>(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view2 = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view2)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView?.setText(list[position].name)
        holder.textView?.setOnClickListener {
            holder.textView?.setTextColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

