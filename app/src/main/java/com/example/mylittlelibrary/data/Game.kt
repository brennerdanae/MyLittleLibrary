package com.example.mylittlelibrary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") @SerializedName("title") var name: String,
    @ColumnInfo(name = "lendTo") var lendTo: String,
    @ColumnInfo(name = "date") var date: String
)
