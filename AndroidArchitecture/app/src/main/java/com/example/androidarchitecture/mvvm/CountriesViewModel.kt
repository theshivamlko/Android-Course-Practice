package com.example.androidarchitecture.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.model.CountryService
import com.example.androidarchitecture.mvp.IAppView
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CountriesViewModel : ViewModel() {

    var listMain: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    var countryService: CountryService

    init {
        countryService = CountryService()
        fetchCountries()
    }

    fun getCountries(): LiveData<List<String>> {
        return listMain
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
                    listMain.postValue(list.toList())
                }
            }

    }

    fun refresh() {
        fetchCountries()
    }

}