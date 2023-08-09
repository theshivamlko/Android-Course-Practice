package com.example.listviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecycleViewActivity : AppCompatActivity() {

    private lateinit var mRecycle: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        mRecycle = findViewById<RecyclerView>(R.id.recycle)


        val type = object : TypeToken<List<Country>>() {}.type
        val list = Gson().fromJson<ArrayList<Country>>(CustomListActivity().jsonCountry, type)
        println(list.size)
        val myRecyclerViewAdpater: MyRecyclerViewAdpater= MyRecyclerViewAdpater(list)
        mRecycle.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mRecycle.adapter=myRecyclerViewAdpater


    }
}