package com.example.daggerexample.example2

import dagger.Component


@Component(
    modules = [AmoledDisplay3rdPartyClassModule::class,
      //  Display2Module::class,
        Display2ModuleWithBind::class
    ]
)
interface MobileComponent2 {

    // function should have same name as Dependent
    fun getMobile(): Mobile2

}