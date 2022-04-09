package com.example.mylittlelibrary.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Game(
    @SerializedName("Name") var name: String,
    var lendTo: String,
    var date: Date
)
