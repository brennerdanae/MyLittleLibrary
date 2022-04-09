package com.example.mylittlelibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") @SerializedName("title") var name: String,
    @ColumnInfo(name = "lendTo") var lendTo: String,
    @ColumnInfo(name = "date") var date: String
)
