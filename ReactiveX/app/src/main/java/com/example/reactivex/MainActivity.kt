package com.example.reactivex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val str = "Hello"

    lateinit var myObservable: Observable<String>
 //   lateinit var myObserver: Observer<String>
    lateinit var disposableObserver: DisposableObserver<String>
  //  lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        singleValueObserver()

    }

    private fun singleValueObserver() {
        myObservable = Observable.just(str)

        // IO Thread
        myObservable.subscribeOn(Schedulers.io())
            // UI Thread
        .observeOn(AndroidSchedulers.mainThread())

        println("new observable")
/*        myObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
          //      disposable=d
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onNext(t: String) {
                println("onNext $t")
            }

        }*/


        // No need of onSubscribe
        disposableObserver=object :DisposableObserver<String>(){
            override fun onNext(t: String) {
                println("onNext $t")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }

        }





        Handler().postDelayed({

            println("SLeep END")

            myObservable.subscribe(disposableObserver)

            Thread.sleep(2000)
            println("SLeep END")
        },4000)


    }


    override fun onPause() {
        super.onPause()
        println("onPause")

   //     disposableObserver.dispose()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}