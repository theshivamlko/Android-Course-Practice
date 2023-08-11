package com.example.noteapproommvvm

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapproommvvm.databinding.FragmentListBinding
import com.example.noteapproommvvm.databinding.ItemListBinding
import com.example.noteapproommvvm.roomdb.NoteEntity
import kotlin.random.Random

class MyAdapter(  ):RecyclerView.Adapter<NoteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val inflator=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val binding=DataBindingUtil.inflate<ItemListBinding>(inflator,R.layout.item_list,parent,false)

        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
         return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note=differ.currentList[position]
        holder.itemListBinding.title.text=note.title
        holder.itemListBinding.description.text=note.description

        val random= Random( 0)
        val color=Color.argb(255,random.nextInt(255),random.nextInt(255),random.nextInt(255))

        holder.itemListBinding.linearLayout.setBackgroundColor(color)
        holder.itemListBinding.linearLayout.setOnClickListener{
            val direction=ListFragmentDirections.actionListFragmentToAddNoteFragment(note)
            holder.itemListBinding.linearLayout.findNavController().navigate(direction)
        }
    }


    val differenceCallback=object:DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id==newItem.id && oldItem.title== newItem.title && oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return  oldItem==newItem
        }
    }

    val differ=AsyncListDiffer(this,differenceCallback)



}



  class NoteViewHolder( val itemListBinding: ItemListBinding) : RecyclerView.ViewHolder( itemListBinding.root) {

  }






