package com.example.reactivex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.reactivex.databinding.ActivityMain2Binding
import com.example.reactivex.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity2 : AppCompatActivity() {

    val str = "MainActivity2 RxKotlin"
    val list1 = listOf<String>("A", "B", "C", "D", "E")

    lateinit var myObservable: Observable<String>
    lateinit var myObservableList: Observable<List<String>>
    lateinit var myObservableStrList: Observable<String>

    //   lateinit var myObserver: Observer<String>
    lateinit var disposableObserver: DisposableObserver<String>
    lateinit var disposableObserverList: DisposableObserver<List<String>>
    lateinit var disposableObserverStrList: DisposableObserver<String>

    //  lateinit var disposable: Disposable
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    lateinit var activityMain2Binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        myObservable = Observable.just(str)

        // myObservableList = Observable.just(list1)
        myObservableStrList = Observable.just("A", "B", "C", "D", "E")
        myObservable = Observable.fromIterable(list1)

        //    singleValueObserver()

          val tempObserver1 = myObservable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(getObserver())


        /*   val tempObserverList1 = myObservableList.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(getObserverList())*/


        val tempObserverList2 = myObservableStrList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getObserver())
    }

    fun getObserverList(): DisposableObserver<List<String>> {
        disposableObserverList = object : DisposableObserver<List<String>>() {
            override fun onNext(t: List<String>) {
                println("onNext getObserverList $t")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }

        }

        return disposableObserverList

    }

    fun getObserver(): DisposableObserver<String> {
        disposableObserver = object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                println("onNext getObserver $t")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onComplete() {
                println("onComplete")
            }

        }

        return disposableObserver
    }


}