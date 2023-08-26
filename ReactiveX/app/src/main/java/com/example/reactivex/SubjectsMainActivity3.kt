package com.example.reactivex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.reactivex.databinding.ActivitySubjectsMain3Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.subjects.UnicastSubject

class SubjectsMainActivity3 : AppCompatActivity() {

    lateinit var activitySubjectsMain3Binding: ActivitySubjectsMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySubjectsMain3Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_subjects_main3)


          asyncSubjects()
//        behaviourSubjects()

        //    publishSubjects()
        /*    Handler().postDelayed({
               behaviourSubjects()

           },3000)*/

     //    replaySubjects()
   //  unicastSubjects()
    }


    fun unicastSubjects() {



        lateinit var myObservable: Observable<String>
        myObservable = Observable.just("A", "B", "C", "D", "E")

        val asyncSubject = UnicastSubject.create<String>(2)

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(asyncSubject)

        asyncSubject.onNext("JAVA")
        asyncSubject.subscribe(getObservable1("unicastSubjects1"))
        asyncSubject.onNext("KOTLIN")
        asyncSubject.onNext("DART")
        asyncSubject.subscribe(getObservable1("unicastSubjects2"))
        asyncSubject.onNext("GO")


    }

    fun replaySubjects() {

        lateinit var myObservable: Observable<String>
        myObservable = Observable.just("A", "B", "C", "D", "E")

        val asyncSubject = ReplaySubject.create<String>()

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(asyncSubject)

        asyncSubject.subscribe(getObservable1("replaySubjects1"))
        asyncSubject.onNext("JAVA")
        asyncSubject.onNext("KOTLIN")
        asyncSubject.onNext("DART")
        asyncSubject.subscribe(getObservable1("replaySubjects2"))
        asyncSubject.onNext("GO")


    }

    fun publishSubjects() {

        lateinit var myObservable: Observable<String>
        myObservable = Observable.just("A", "B", "C", "D", "E")

        val asyncSubject = PublishSubject.create<String>()

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(asyncSubject)

        asyncSubject.subscribe(getObservable1("publishSubjects1"))
        asyncSubject.onNext("JAVA")
        asyncSubject.onNext("KOTLIN")
        asyncSubject.onNext("DART")
        asyncSubject.subscribe(getObservable1("publishSubjects2"))
        asyncSubject.onNext("GO")


    }

    fun behaviourSubjects() {

        lateinit var myObservable: Observable<String>
        myObservable = Observable.just("A", "B", "C", "D", "E")

        val asyncSubject = BehaviorSubject.create<String>()

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(asyncSubject)

        asyncSubject.subscribe(getObservable1("behaviourSubjects1"))
        asyncSubject.onNext("JAVA")
        asyncSubject.onNext("KOTLIN")
        asyncSubject.onNext("DART")
        asyncSubject.subscribe(getObservable1("behaviourSubjects2"))
        asyncSubject.onNext("GO")

    }

    fun asyncSubjects() {

        lateinit var myObservable: Observable<String>
        myObservable = Observable.just("A", "B", "C", "D", "E")

        val asyncSubject = AsyncSubject.create<String>()

        myObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(asyncSubject)

        asyncSubject.subscribe(getObservable1("asyncSubjects1"))
        asyncSubject.onNext("JAVA")
        asyncSubject.onNext("KOTLIN")
        asyncSubject.onNext("DART")
        asyncSubject.subscribe(getObservable1("asyncSubjects2"))
        asyncSubject.onNext("GO")
        asyncSubject.onComplete()


    }

    fun getObservable1(tag: String): Observer<String> {
        return object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                println("onNext $tag $t")

            }

            override fun onError(e: Throwable) {
                println("onError $tag $e")
            }

            override fun onComplete() {
                println("onComplete $tag")
            }

        }
    }
}