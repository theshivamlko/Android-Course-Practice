package com.example.listviewapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyAdapter(val appcontext:Context,val list:ArrayList<Country>):ArrayAdapter<Country>(appcontext,R.layout.item_list) {


    override fun getCount(): Int {
        return list.size
    }
    override fun getItemId(position:Int):Long{
        return 0
    }

    override fun getItem(position: Int): Country? {
        return list[position]
    }


    override fun getView(position:Int,   convertView:View?, group:ViewGroup):View{

        val view1:View?
        var viewHolder:MyViewHolder

        if(convertView==null){
            val inflator =context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view1=inflator.inflate(  R.layout.item_list,null)
            viewHolder =MyViewHolder(view1)
            viewHolder.textView?.setOnClickListener {
                list.get(position).bool=true
                viewHolder.textView?.setTextColor(Color.RED)
                notifyDataSetChanged();
            }

            view1.tag=viewHolder
        }
        else{
            view1=convertView
        }
            viewHolder=view1?.tag as MyViewHolder

        val item: Country? = getItem(position)

        viewHolder.textView?.text=list.get(position).name
        println("${item?.name} ${item?.bool} ------ ${position}")
        if(item?.bool == true){
            println(" SEELECTED")
            viewHolder.textView?.setTextColor(Color.RED)
        }else{
            println(" UNSEELECTED---")
            viewHolder.textView?.setTextColor(Color.BLACK)

        }
      //  notifyDataSetChanged();

        return view1

    }

}

private  class MyViewHolder(item:View?){
    var textView:TextView?=null
    init {
        textView=item?.findViewById<TextView>(R.id.textView)
    }
}