package com.example.mylittlelibrary.data
import com.google.gson.annotations.SerializedName
import java.util.*

data class Book(@SerializedName("name") var name : String, var lendTo: String, var date: Date)
{
    val bookString= name+" "+lendTo
    override fun toString(): String {
        return bookString
    }

}
