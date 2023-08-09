package com.example.fragmetapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var mFrame1: FrameLayout
    private lateinit var mFrame2: FrameLayout
    private lateinit var button: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFrame1 = findViewById<FrameLayout>(R.id.frame1)
        mFrame2 = findViewById<FrameLayout>(R.id.frame2)
        button = findViewById<Button>(R.id.button)
        button2 = findViewById<Button>(R.id.button2)
        val f1:Fragment =MyFragment1()
        val f2:Fragment =MyFragment2()

        var count=0;

        button.setOnClickListener {
            val transaction:FragmentTransaction=supportFragmentManager.beginTransaction();
            if(count==0) {
                transaction.replace(R.id.frame1,f1 ).commitAllowingStateLoss()
                count++
            }else{
                transaction.replace(R.id.frame1,f2 ).commit()
            }
        }


        button2.setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction();
            if(count==0) {
                transaction.add(R.id.frame2, f2).commitAllowingStateLoss()
                count++
            }else{
                transaction.add(R.id.frame2,f1 ).commit()
            }

        }

    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        println("onPostCreate")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")

    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        println("onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        println("onDetachedFromWindow")
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        println("onCreateContextMenu")

    }

    override fun onPostResume() {
        super.onPostResume()
        println("onDetachedFromWindow")
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        println("onActivityReenter")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        println("onActivityReenter")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        println("onTopResumedActivityChanged")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        println("onRestoreInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        println("onRestoreInstanceState persistentState")
    }
}