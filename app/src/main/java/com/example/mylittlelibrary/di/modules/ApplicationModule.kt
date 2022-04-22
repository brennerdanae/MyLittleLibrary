package com.example.mylittlelibrary.di.modules

import android.content.Context
import com.example.mylittlelibrary.MyLittleLibraryApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyLittleLibraryApplication) {

    @Singleton
    @Provides
    fun provideApplication() = application

    @Singleton
    @Provides
    fun applicationContext(): Context = application.applicationContext
}