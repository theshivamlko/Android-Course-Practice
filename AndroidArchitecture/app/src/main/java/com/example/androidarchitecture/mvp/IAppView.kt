package com.example.androidarchitecture.mvp

import com.example.androidarchitecture.model.Country

interface IAppView {

    fun setValues(list:List<String>)
    fun onError()
    fun refresh()
}