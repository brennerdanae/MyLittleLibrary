package com.example.mylittlelibrary.di.modules

import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.room.BookRoomDatabase
import com.example.mylittlelibrary.service.BookService
import dagger.Module
import dagger.Provides

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

    @Provides
    fun provideMovieDao(bookRoomDatabase: BookRoomDatabase) = bookRoomDatabase.movieDao()

    @Provides
    fun provideDvdDao(bookRoomDatabase: BookRoomDatabase) = bookRoomDatabase.dvdDao()
}