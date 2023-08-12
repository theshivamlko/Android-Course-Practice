package com.example.kcoroutines

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.findFragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.kcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var counter=1;

    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        binding.fab.setOnClickListener { view ->

            when(counter){
                1->{
                    ++counter
                    dataFromMainThread()
                }
                2->{
                    ++counter
                    dataFromThread1()
                }
                3->{
                    ++counter
                    dataFromThread2()
                }
            }


            // Local
          /*  CoroutineScope(Dispatchers.IO).launch {
                downloadFile()
            }  */


            // Global
          /*  kotlinx.coroutines.GlobalScope .launch {
                downloadFile()
            }*/

        }



    }

    fun dataFromMainThread(){

        CoroutineScope(Dispatchers.Main).launch {

            binding.textView.setText("Hello from MAIN")
        }
    }
    fun dataFromThread1(){

        CoroutineScope(Dispatchers.IO).launch {
            binding.textView.setText("Hello from IO")

        }
    }

    fun dataFromThread2(){
        CoroutineScope(Dispatchers.Unconfined).launch {
            binding.textView.setText("Hello from Unconfined")

        }
    }

    private fun downloadFile(){
        for (i in 1..10000000){
            Log.e(TAG,"downloadFile ${Thread.currentThread().name}  ${Thread.currentThread().id} $i")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}