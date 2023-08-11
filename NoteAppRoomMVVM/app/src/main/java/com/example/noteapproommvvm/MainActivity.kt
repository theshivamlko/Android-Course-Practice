package com.example.noteapproommvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.noteapproommvvm.databinding.ActivityMainBinding
import com.example.noteapproommvvm.livedata.NOteViewModelFactory
import com.example.noteapproommvvm.livedata.NoteViewModel
import com.example.noteapproommvvm.roomdb.NoteRoomDatabase

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    lateinit var noteRepositories: NoteRepositories
    lateinit var nOteViewModelFactory: NOteViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val db = NoteRoomDatabase.getInstance(this@MainActivity)
        noteRepositories = NoteRepositories(db)
        nOteViewModelFactory=NOteViewModelFactory(application,noteRepositories)

        noteViewModel = ViewModelProvider(this,nOteViewModelFactory).get(NoteViewModel::class.java)



    }
}