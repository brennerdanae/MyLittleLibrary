package com.example.mylittlelibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "lendTo") var lendTo: String,
    @ColumnInfo(name = "date") var date: String
)
