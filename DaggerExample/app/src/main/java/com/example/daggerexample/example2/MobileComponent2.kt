package com.example.daggerexample.example2

import dagger.Component


@Component
interface MobileComponent2 {


    // function should have same name as Dependent
    fun getMobile(): Mobile2

}