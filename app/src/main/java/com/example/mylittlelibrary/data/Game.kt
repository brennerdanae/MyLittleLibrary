package com.example.mylittlelibrary.data

//import androidx.room.ColumnInfo
//import androidx.room.PrimaryKey
import java.util.*

data class Game(var name: String, var lendTo: String, var date: Date){
    override fun toString(): String {
        return name
    }
}
