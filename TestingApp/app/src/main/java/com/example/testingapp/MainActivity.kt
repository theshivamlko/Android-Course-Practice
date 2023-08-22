package com.example.testingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testingapp.playlisttest.PlayListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Playlist")

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .add(R.id.linear1, PlayListFragment.newInstance()).commit()
        }

    }
}