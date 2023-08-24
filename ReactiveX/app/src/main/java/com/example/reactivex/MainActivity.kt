package com.example.reactivex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val str = "Hello"

    lateinit var myObservable: Observable<String>
    lateinit var myObserver: Observer<String>
    lateinit var disposable: Disposable

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
        myObservable.observeOn(AndroidSchedulers.mainThread())

        println("new observable")
        myObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
                disposable=d
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

        }

        Thread.sleep(4000)
        println("SLeep END")

        myObservable.subscribe(myObserver)
    }


    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}