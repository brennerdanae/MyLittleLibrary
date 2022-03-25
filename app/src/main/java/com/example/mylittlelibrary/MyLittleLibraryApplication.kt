package com.example.mylittlelibrary

import android.app.Application
import com.example.mylittlelibrary.di.AppComponent
import com.example.mylittlelibrary.di.DaggerAppComponent
import com.example.mylittlelibrary.di.modules.ApplicationModule

class MyLittleLibraryApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build();
    }
}