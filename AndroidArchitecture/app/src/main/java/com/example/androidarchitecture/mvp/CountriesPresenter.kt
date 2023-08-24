package com.example.androidarchitecture.mvp

import com.example.androidarchitecture.model.CountryService
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CountriesPresenter(IAppView1: IAppView) {
    var iAppView: IAppView
    var countryService: CountryService

    init {

        iAppView = IAppView1
        countryService = CountryService()
        fetchCountries()
    }

    fun fetchCountries() {

        countryService.getCountries().subscribeOn(Schedulers.newThread())
            .subscribe {
                println("fetchCountries $it")
                countryService.getCountries()

                runBlocking(Dispatchers.Main) {
                    var list = mutableListOf<String>()
                    it.forEach {
                        list.add(it.countryName)
                    }
                    iAppView.setValues(list.toList())
                }
            }

    }

    fun refresh() {
        fetchCountries()
    }
}