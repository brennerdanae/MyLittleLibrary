package com.example.mylittlelibrary.di.modules

import android.content.Context
import androidx.room.Room
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.room.BookRoomDatabase
import com.example.mylittlelibrary.service.BookService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MyLittleLibraryModule {

    @Provides
    fun provideBookDatabase(
        application: MyLittleLibraryApplication
    ) = BookRoomDatabase.getDatabase(application)

    @Provides
    fun bookService() = BookService()

    @Provides
    fun provideBooksDao(bookRoomDatabase: BookRoomDatabase) = bookRoomDatabase.bookDao()
}