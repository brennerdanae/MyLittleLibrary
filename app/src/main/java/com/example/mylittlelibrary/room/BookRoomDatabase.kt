package com.example.mylittlelibrary.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.room.dao.BookDao
import com.example.mylittlelibrary.room.dao.DvdDao
import com.example.mylittlelibrary.room.dao.MovieDao

@Database(
    entities = arrayOf(Book::class, Movie::class, Dvd::class), version = 1,
    exportSchema = false
)
public abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun movieDao(): MovieDao
    abstract fun dvdDao(): DvdDao

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(context: Context): BookRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}