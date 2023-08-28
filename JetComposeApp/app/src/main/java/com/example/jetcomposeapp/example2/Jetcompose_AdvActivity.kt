package com.example.jetcomposeapp.example2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.withCreated
import com.example.jetcomposeapp.MainActivity
import com.example.jetcomposeapp.R
import com.example.jetcomposeapp.databinding.ActivityJetomposeAdvBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class Jetcompose_AdvActivity : AppCompatActivity() {

    lateinit var activityJetomposeAdvBinding: ActivityJetomposeAdvBinding
    lateinit var viewModel1: MyViewModel1
    val data = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityJetomposeAdvBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_jetompose_adv)

        activityJetomposeAdvBinding.lifecycleOwner = this

        viewModel1 = ViewModelProvider(this).get(MyViewModel1::class.java)


        activityJetomposeAdvBinding.myViewModel = viewModel1
        viewModel1.editText.observe(this) {
            println("Jetcompose_AdvActivity $it")
        }


        activityJetomposeAdvBinding.textView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        /* data.observe(this){
             println("MutableLiveData $it ")
             activityJetomposeAdvBinding.textView.text=it
         }*/

        /*  println("lifecycleScope 1")
          lifecycleScope.launch(Dispatchers.IO) {
              println("lifecycleScope launch")
               run()
              println("lifecycleScope END")

          }*/

        /*        val abc = liveData<String>() {
                    println("liveData launch")
                    run()
                    println("liveData END")
                    activityJetomposeAdvBinding.textView.text = "WWWWWWW"

                    emit("ABC")


                }*/
        /*  abc.observe(this) {
              println("abc $it")
          }*/

        /*ABC(object : MutableLiveData<String>(){

            override fun setValue(value: String?) {
                super.setValue(value)
                activityJetomposeAdvBinding.textView.text = value


            }}
          )*/

        lifecycleScope.launch {
            viewModel1.getDataFlow().collect {
                println("getDataFlow $it")
            }
        }




    }

    suspend fun run() {

        /* for (i in 1..100000000){
             println("IO ${Thread.currentThread().name}")

         }*/




        delay(5000L)

        withContext(newSingleThreadContext("AA")) {
            try {
                val a = 122 / 0
            } catch (e: Exception) {

            }
        }

        println("IO ${Thread.currentThread().name}")
        activityJetomposeAdvBinding.textView.text = "QQQQQQQ"

        data.postValue("Aaaaaaaaa")
        // here
        println("Unconfined3 ${Thread.currentThread().name}")
        delay(5000L)
        println("Unconfined4 ${Thread.currentThread().name}")


    }

    override fun onPause() {
        super.onPause()
        println("Jetcompose_AdvActivity onPause ${viewModel1.editText.value}")
    }


    override fun onResume() {
        super.onResume()
        println("Jetcompose_AdvActivity onResume ${viewModel1.editText.value}")
    }

    override fun onRestart() {
        super.onRestart()
        println("Jetcompose_AdvActivity onRestart ${viewModel1.editText.value}")
    }

    override fun onStart() {
        super.onStart()
        println("Jetcompose_AdvActivity onStart ${viewModel1.editText.value}")

    }

    override fun onStop() {
        super.onStop()
        println("Jetcompose_AdvActivity onStop ${viewModel1.editText.value}")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("Jetcompose_AdvActivity onDestroy ${viewModel1.editText.value}")

    }

    override fun finish() {
        super.finish()
        println("Jetcompose_AdvActivity finish ${viewModel1.editText.value}")

    }

    class ABC(value: MutableLiveData<String>) {
        init {


            Handler().postDelayed({

                value.postValue("Hellow")
            }, 3000)
        }

    }

}