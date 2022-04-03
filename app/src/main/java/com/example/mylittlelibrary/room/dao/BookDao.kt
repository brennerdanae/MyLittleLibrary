package com.example.mylittlelibrary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylittlelibrary.data.Book
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.Continuation

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table ORDER BY name ASC")
    fun getAllBooks(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(book: List<Book>)

}