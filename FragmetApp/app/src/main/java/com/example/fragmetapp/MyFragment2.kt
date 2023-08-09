package com.example.fragmetapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MyFragment2:Fragment() {


    override fun onCreateView(inflator:LayoutInflater,group: ViewGroup?,bundel: Bundle?): View?{


        return inflator.inflate(R.layout.fragment2,group,false)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("onAttach MyFragment2")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate MyFragment2")
    }


    override fun onStart() {
        super.onStart()
        println("onStart MyFragment2")
    }


    override fun onResume() {
        super.onResume()
        println("onResume MyFragment2")
    }


    override fun onPause() {
        super.onPause()
        println("onPause MyFragment2")
    }




    override fun onStop() {
        super.onStop()
        println("onStop MyFragment2")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView MyFragment2")
    }
    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy MyFragment2")
    }

    override fun onDetach() {
        super.onDetach()
        println("onDetach MyFragment2")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated MyFragment2")
    }

}