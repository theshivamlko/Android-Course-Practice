package com.example.daggerexample.example2

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AmoledDisplay3rdPartyClassModule::class,
      //  Display2Module::class,
        Display2ModuleWithBind::class
    ]
)
interface MobileComponent2 {

    // function should have same name as Dependent
    fun getMobile(): Mobile2


    // Allow Injection thru Activity/Fragment only
    fun inject(activity: Example2Activity)

}