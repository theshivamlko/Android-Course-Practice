package com.example.daggerexample.example2

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AmoledDisplay3rdPartyClassModule3::class,
      //  Display2Module::class,
        Display3ModuleWithBind::class
    ]
)
interface MobileComponent3 {

    // function should have same name as Dependent
   // @Named("mobile")
    fun getMobile(): Mobile3


    // Allow Injection thru Activity/Fragment only
    fun inject(activity: Example3Activity)

}