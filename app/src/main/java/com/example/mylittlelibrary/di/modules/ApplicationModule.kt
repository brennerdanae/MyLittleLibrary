package com.example.mylittlelibrary.di.modules

import com.example.mylittlelibrary.MyLittleLibraryApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyLittleLibraryApplication) {

    @Singleton
    @Provides
    fun provideApplication() = application

}