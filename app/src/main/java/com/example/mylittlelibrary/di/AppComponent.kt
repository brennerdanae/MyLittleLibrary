package com.example.mylittlelibrary.di

import com.example.mylittlelibrary.di.modules.ApplicationModule
import com.example.mylittlelibrary.di.modules.MyLittleLibraryModule
import com.example.mylittlelibrary.ui.activities.AddBookActivity
import com.example.mylittlelibrary.ui.activities.AddDvdActivity
import com.example.mylittlelibrary.ui.activities.AddMovieActivity
import com.example.mylittlelibrary.ui.activities.ListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MyLittleLibraryModule::class,
        ApplicationModule::class
    ]
)
interface AppComponent {
    fun inject(activity: ListActivity)

    fun inject(activity: AddBookActivity)

    fun inject(activity: AddMovieActivity)

    fun inject(activity: AddDvdActivity)
}