package com.example.mylittlelibrary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylittlelibrary.data.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("Select * From movie_table Order By name ASC" )
    fun getAllMovies(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<Movie>)

    @Query("Delete from movie_table")
    suspend fun deleteAll()
}