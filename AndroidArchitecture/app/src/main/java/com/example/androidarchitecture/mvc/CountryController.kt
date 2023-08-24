package com.example.androidarchitecture.mvc

import com.example.androidarchitecture.model.Country
import com.example.androidarchitecture.model.CountryService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CountryController(private val mvcActivity2: MVCActivity) {

      var mvcActivity: MVCActivity
      var countryService: CountryService

    init {

        mvcActivity = mvcActivity2
        countryService = CountryService()
        fetchCountries()
    }

    fun fetchCountries() {
        countryService.getCountries().subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
            .subscribe {
                println("fetchCountries $it")
                countryService.getCountries()
                runBlocking(Dispatchers.Main) {
                    var list = mutableListOf<String>()
                    it.forEach {
                        list.add(it.countryName)
                    }
                    mvcActivity.setValues(list.toList())
                }


            }


    }

    fun refresh() {
        fetchCountries()
    }
}