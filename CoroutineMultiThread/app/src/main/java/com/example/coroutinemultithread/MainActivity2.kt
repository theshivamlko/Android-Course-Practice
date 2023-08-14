package com.example.coroutinemultithread

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devtides.imageprocessingcoroutines.Filter
import com.example.coroutinemultithread.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity2 : AppCompatActivity() {

    var url =
        "https://images.unsplash.com/photo-1611003228941-98852ba62227?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1548&q=80"
    lateinit var actvityMainBinding: ActivityMainBinding

    val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actvityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        coroutineScope.launch {
            val task = coroutineScope.async(Dispatchers.IO) {
                getBitmap()
            }

            val originalBitmap = task.await()
            loadImage(originalBitmap)

           // delay(2000L)
            /*val bit = Filter.apply(originalBitmap)
            loadImage(bit)*/
        }

    }

    private fun getBitmap(): Bitmap {
        return URL(url).openStream().use {
            BitmapFactory.decodeStream(it)
        }
    }

    private fun loadImage(bitmap: Bitmap) {
        actvityMainBinding.imageView.setImageBitmap(bitmap)
        val bit = Filter.apply(bitmap)
        actvityMainBinding.imageView.setImageBitmap(bit)

    }
}