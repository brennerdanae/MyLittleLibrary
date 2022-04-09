package com.example.mylittlelibrary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylittlelibrary.data.Dvd
import kotlinx.coroutines.flow.Flow

@Dao
interface DvdDao {
    @Query("Select * From dvd_table Order By name ASC" )
    fun getAllDvd(): Flow<List<Dvd>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDvd(dvd: Dvd)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dvd: List<Dvd>)

    @Query("Delete from dvd_table")
    suspend fun deleteAll()
}