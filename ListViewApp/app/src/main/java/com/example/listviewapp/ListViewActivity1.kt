package com.example.listviewapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class ListViewActivity1 : AppCompatActivity() {

    private lateinit var mListview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view1)


        mListview = findViewById<ListView>(R.id.listview)

        val world_cup_array= generateSequence(1,{
            it+5
        }).take(100).toList()


        val adapter1:ArrayAdapter<Int> = ArrayAdapter(this,android.R.layout.simple_list_item_1,world_cup_array)

        mListview.adapter=adapter1

        mListview.setOnItemClickListener { adapterView, view, i, l ->

           var text:TextView= view.findViewById(android.R.id.text1)
            println("$i $l")
            text.setTextColor(Color.RED)

        }

    }
}