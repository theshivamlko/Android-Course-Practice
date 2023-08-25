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
        //   myObservableStrList = Observable.just("A", "B", "C", "D", "E")
        //    myObservable = Observable.fromIterable(list1)

        //    singleValueObserver()

        /*  val tempObserver1 = myObservable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(getObserver())*/


        /*   val tempObserverList1 = myObservableList.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(getObserverList())*/


        /*   val tempObserverList2 = myObservableStrList.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(getObserver())*/

        // range()

        // create()

        //  createMap()
       // flatMap()
      //  concatMap()
        bufferMap()
    }

    fun bufferMap() {
        lateinit var myObservableInt: Observable<Int>
        myObservableInt = Observable.range(1, 20)

        myObservableInt.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(5)
            .subscribeWith(object : DisposableObserver<List<Int>>() {
                override fun onNext(t: List<Int>) {
                    println("onNext bufferMap $t")
                    Thread.sleep(1000)
                }

                override fun onError(e: Throwable) {
                    println("onError bufferMap")
                }

                override fun onComplete() {
                    println("onComplete bufferMap")
                }

            })


    }
    fun concatMap() {
        val list =
            listOf<Student>(Student(1, "A"), Student(2, "B"), Student(3, "C"), Student(4, "D"))
        lateinit var myObservable: Observable<Student>
        myObservable = Observable.create() { emitter ->
            list.forEach {
             //   Thread.sleep(200)
                emitter.onNext(it)
            }
        //    Thread.sleep(200)
            emitter.onComplete()
        }

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .concatMap {
               val s1= Student(11, "A11")
               val s2= Student(12, "A12")

                it.name = it.name + " ${System.currentTimeMillis()}"
                Observable.just(it,s1,s2)
            }
            .subscribeWith(object : DisposableObserver<Student>() {
                override fun onNext(t: Student) {
                    println("onNext flatMap ${t.roll} ${t.name}")
                }

                override fun onError(e: Throwable) {
                    println("onError flatMap")
                }

                override fun onComplete() {
                    println("onComplete flatMap")
                }

            })


    }
    fun flatMap() {
        val list =
            listOf<Student>(Student(1, "A"), Student(2, "B"), Student(3, "C"), Student(4, "D"))
        lateinit var myObservable: Observable<Student>
        myObservable = Observable.create() { emitter ->
            list.forEach {
             //   Thread.sleep(200)
                emitter.onNext(it)
            }
        //    Thread.sleep(200)
            emitter.onComplete()
        }

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
               val s1= Student(11, "A11")
               val s2= Student(12, "A12")

                it.name = it.name + " ${System.currentTimeMillis()}"
                  Thread.sleep(1000)
                Observable.just(it,s1,s2)
            }
            .subscribeWith(object : DisposableObserver<Student>() {
                override fun onNext(t: Student) {
                    println("onNext flatMap ${t.roll} ${t.name}")
                }

                override fun onError(e: Throwable) {
                    println("onError flatMap")
                }

                override fun onComplete() {
                    println("onComplete flatMap")
                }

            })


    }

    fun createMap() {
        val list =
            listOf<Student>(Student(1, "A"), Student(2, "B"), Student(3, "C"), Student(4, "D"))
        lateinit var myObservable: Observable<Student>
        myObservable = Observable.create() { emitter ->
            list.forEach {
                Thread.sleep(200)
                emitter.onNext(it)
            }
            Thread.sleep(200)
            emitter.onComplete()
        }

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.name = it.name + " ${System.currentTimeMillis()}"
                it
            }
            .subscribeWith(object : DisposableObserver<Student>() {
                override fun onNext(t: Student) {
                    println("onNext createMap ${t.roll} ${t.name}")
                }

                override fun onError(e: Throwable) {
                    println("onError createMap")
                }

                override fun onComplete() {
                    println("onComplete createMap")
                }

            })


    }


    fun create() {
        val list =
            listOf<Student>(Student(1, "A"), Student(2, "B"), Student(3, "C"), Student(4, "D"))
        lateinit var myObservable: Observable<Student>
        myObservable = Observable.create() { emitter ->
            list.forEach {
                Thread.sleep(200)
                emitter.onNext(it)
            }
            emitter.onComplete()
        }

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Student>() {
                override fun onNext(t: Student) {
                    println("onNext create ${t.roll} ${t.name}")
                }

                override fun onError(e: Throwable) {
                    println("onError create")
                }

                override fun onComplete() {
                    println("onComplete create")
                }

            })


    }

    fun range() {
        lateinit var myObservableInt: Observable<Int>
        myObservableInt = Observable.range(1, 20)

        myObservableInt.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Int>() {
                override fun onNext(t: Int) {
                    println("onNext range $t")
                }

                override fun onError(e: Throwable) {
                    println("onError range")
                }

                override fun onComplete() {
                    println("onComplete range")
                }

            })


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