package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.presentation.MainActivity
import dagger.Subcomponent


@MovieScoop
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieActivity:MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieSubComponent
    }


}