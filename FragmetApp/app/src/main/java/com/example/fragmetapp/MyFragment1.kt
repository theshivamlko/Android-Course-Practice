package com.example.fragmetapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MyFragment1:Fragment() {

    override fun onCreateView(inflator:LayoutInflater,group: ViewGroup?,bundel: Bundle?): View?{
        println("onCreateView MyFragment1")

        return inflator.inflate(R.layout.fragment1,group,false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("onAttach MyFragment1")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate MyFragment1")
    }


    override fun onStart() {
        super.onStart()
        println("onStart MyFragment1")
    }


    override fun onResume() {
        super.onResume()
        println("onResume MyFragment1")
    }


    override fun onPause() {
        super.onPause()
        println("onPause MyFragment1")
    }




    override fun onStop() {
        super.onStop()
        println("onStop MyFragment1")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView MyFragment1")
    }
    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy MyFragment1")
    }

    override fun onDetach() {
        super.onDetach()
        println("onDetach MyFragment1")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated MyFragment1")
    }
}