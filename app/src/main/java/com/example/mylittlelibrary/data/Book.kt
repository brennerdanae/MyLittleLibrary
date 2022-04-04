package com.example.mylittlelibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "book_table")
data class Book( @PrimaryKey val id: Int, @ColumnInfo(name = "name") var name: String, @ColumnInfo(name = "lendTo") var lendTo: String, @ColumnInfo(name = "date") var date: String){
    override fun toString(): String {
        return name
    }
}

