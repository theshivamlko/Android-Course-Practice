package com.example.jetcomposeapp.example2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
 import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.jetcomposeapp.ConstraintlayoutActivity4
import com.example.jetcomposeapp.MainActivity
import com.example.jetcomposeapp.R
import com.example.jetcomposeapp.databinding.ActivityJetomposeAdvBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class Jetcompose_AdvActivity : AppCompatActivity() {

    lateinit var activityJetomposeAdvBinding: ActivityJetomposeAdvBinding
    lateinit var viewModel1: MyViewModel1
    val data = MutableLiveData<String>()

    lateinit var notificationManager: NotificationManager
    val channelId = "ABC"
    val key_search = "key_search"


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

        lifecycleScope.launch {
            viewModel1.stateFlow.collect{
                println("stateFlow $it")
            }
        }
        viewModel1.updateStateFlow()

        Handler().postDelayed({
            lifecycleScope.launch {
                viewModel1.sharedFlow.collect{
                    println("sharedFlow $it")
                }
            }
           viewModel1.updateSharedFlow()


        },3000)

        Handler().postDelayed({
            println("New Observer")
            lifecycleScope.launch {

                viewModel1.stateFlow.collect{
                    println("stateFlow $it")
                }

            }
            lifecycleScope.launch {

                viewModel1.sharedFlow.collect{
                    println("sharedFlow $it")
                }

            }


        },10000)

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


                }*//*  abc.observe(this) {
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

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        activityJetomposeAdvBinding.button1.setOnClickListener {
            createNotificationChannel(
                "RandomChannelName", "Random Description"
            )

            Handler().postDelayed({
                simpleNotification("My Title", "My Description")

            }, 3000)

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


    fun simpleNotification(title: String, description: String) {

        val intent = Intent(this, ConstraintlayoutActivity4::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val contentpendingIntent =
            PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_MUTABLE)

        val intent1 = Intent(this, MainActivity::class.java)
        intent1.putExtra("page", "Notification REPLY")
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val action1pendingIntent =
            PendingIntent.getActivity(
                this,
                2,
                intent1,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        val action1 = NotificationCompat.Action.Builder(
            android.R.drawable.btn_plus, "REPLY", action1pendingIntent
        )

        val intent2 = Intent(this, MainActivity::class.java)
        intent2.putExtra("page", "Notification DELETE")
        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val action2pendingIntent =
            PendingIntent.getActivity(
                this, 3, intent2,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val action2 = NotificationCompat.Action.Builder(
            android.R.drawable.ic_delete, "DELETE", action2pendingIntent
        )


        // Search Action
        val remoteInput= RemoteInput.Builder(key_search).apply {
            setLabel("Search here...")


        }.build()


        val intent3 = Intent(this, MainActivity::class.java)
        intent3.putExtra("page", "Notification SEARCH")
        intent3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)


        val action3pendingIntent =
            PendingIntent.getActivity(
                this,
                2,
                intent3,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        val action3 = NotificationCompat.Action.Builder(
            android.R.drawable.ic_menu_search, "SEARCH", action3pendingIntent
        ).apply {
            addRemoteInput(remoteInput)
        }.build()

        val notification = NotificationCompat.Builder(this, channelId).apply {
            setContentTitle(title)
            setContentText(description)
            setSmallIcon(android.R.drawable.btn_radio)
            setAutoCancel(true)
            setDefaults(Notification.DEFAULT_SOUND)

            setContentIntent(contentpendingIntent)
            addAction(action1.build())
            addAction(action2.build())
            addAction(action3)
        }.build()

        val notificationID = 100
        notificationManager.notify(notificationID, notification)


    }

    fun createNotificationChannel(channelName: String, description: String) {
        println("createNotification ${Build.VERSION.SDK_INT} ${Build.VERSION_CODES.O}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                this.description = description
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                setShowBadge(true)
                enableLights(true)
            }
            notificationManager.createNotificationChannel(channel)

        }


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