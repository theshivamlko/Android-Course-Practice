package com.example.musicplayer

import android.icu.util.TimeUnit
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View.OnScrollChangeListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment

class MainActivity : ComponentActivity() {
    var handler: Handler = Handler (Looper.getMainLooper())

    lateinit var mediaPlayer: MediaPlayer
    var totalTime: Double = 0.0
    var startTime: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val songName: TextView by lazy { findViewById<TextView>(R.id.songName) }
        val timer: TextView by lazy { findViewById<TextView>(R.id.timer) }
        val seekbar: SeekBar by lazy { findViewById<SeekBar>(R.id.seekbar) }
        val backButton: ImageButton by lazy { findViewById<ImageButton>(R.id.back_button) }
        val pauseButton: ImageButton by lazy { findViewById<ImageButton>(R.id.pause_button) }
        val playButton: ImageButton by lazy { findViewById<ImageButton>(R.id.play_button) }
        val fwdButton: ImageButton by lazy { findViewById<ImageButton>(R.id.fwd_button) }


        // Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.suzume)

        seekbar.isClickable = false
        songName.text="${resources.getResourceEntryName(R.raw.suzume)}"


        pauseButton.setOnClickListener {
            println("pauseButton()")
            mediaPlayer.pause()
        }
        fwdButton.setOnClickListener {
            println("fwdButton()")
            var temp =startTime
            if((temp+10000) <= totalTime){  // 10 sec
                startTime += 10000
                println("fwdButton ${startTime.toInt()}")
                mediaPlayer.seekTo(startTime.toInt())
            }else{
                Toast.makeText(this,"Cannot Forward!",Toast.LENGTH_LONG).show()
            }
        }


        backButton.setOnClickListener {
            println("backButton()")
            var temp =startTime
            if((temp-10000)>  0){
                startTime=startTime-10000
                println("backButton ${startTime.toInt()}")
                mediaPlayer.seekTo(startTime.toInt())
            }else{
                Toast.makeText(this,"Cannot Back!",Toast.LENGTH_LONG).show()
            }
        }

        mediaPlayer.setOnBufferingUpdateListener { player, i ->
            println("setOnBufferingUpdateListener")
       //     seekbar.setProgress(player.currentPosition)
        }

        val updateTime: Runnable = object :Runnable {
            override fun run() {
              //  println("Runnable start")
                startTime = mediaPlayer.currentPosition.toDouble()
                timer.text = String.format(
                    "%d min, %d sec",
                    java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                    java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(startTime.toLong())
                            - java.util.concurrent.TimeUnit.MINUTES.toSeconds(
                        java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(
                            startTime.toLong()
                        )
                    )
                )
          //     println("Runnable $startTime $totalTime")
                seekbar.progress=startTime.toInt()
                if(startTime<=totalTime) {
                    handler.postDelayed(this, 100)
                }else{
                    handler.removeCallbacks(this);
                }
            }
        }

        playButton.setOnClickListener {
            println("playButton()")
            mediaPlayer.start()
            totalTime = mediaPlayer.duration.toDouble()
            println("totalTime $totalTime ")
            seekbar.max =  mediaPlayer.duration
            startTime = mediaPlayer.currentPosition.toDouble()
            timer.setText("${totalTime - startTime}")
            handler.postDelayed(updateTime,100)

        }


        seekbar.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    if(p0!=null) {
                        startTime = p0.progress.toDouble()
                        println("backButton ${p0.progress.toDouble()}")
                        mediaPlayer.seekTo(p0.progress )
                    }
                }

                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {


                }

            }

        )

    }
}

