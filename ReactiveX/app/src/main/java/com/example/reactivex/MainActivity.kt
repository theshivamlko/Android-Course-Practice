package com.example.reactivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.databinding.DataBindingUtil
import com.example.reactivex.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val str = "Hello"
    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var myObservable: Observable<String>

    //   lateinit var myObserver: Observer<String>
    lateinit var disposableObserver: DisposableObserver<String>
    lateinit var disposableObserver2: DisposableObserver<String>

    //  lateinit var disposable: Disposable
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.button.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }


        singleValueObserver()

    }

    private fun singleValueObserver() {
        myObservable = Observable.just(str)

        // IO Thread
        //   myObservable.subscribeOn(Schedulers.io())
        // UI Thread
        //     .observeOn(AndroidSchedulers.mainThread())

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
        disposableObserver = object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                println("onNext Observer1 $t")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }

        }

        disposableObserver2 = object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                println("onNext Observer2 $t")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }

        }

        val tempObserver1 = myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(disposableObserver)
        println("tempObserver1 ${tempObserver1}")


//     val tempObserver2=   myObservable.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(disposableObserver2)
//        println("tempObserver2 ${tempObserver2}")


        Handler().postDelayed({

            //     compositeDisposable.add(tempObserver1)
            //     compositeDisposable.add(tempObserver2)

//            compositeDisposable.add(disposableObserver)
//            compositeDisposable.add(disposableObserver2)

            // Subscribe  observer 1 time only
            //  myObservable.subscribe(disposableObserver)
            //     myObservable.subscribe(disposableObserver2)


            println("SLeep END")
        }, 4000)


    }


    override fun onPause() {
        super.onPause()
        println("onPause")

        //     disposableObserver.dispose()
        //     disposableObserver2.dispose()
        compositeDisposable.dispose()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}