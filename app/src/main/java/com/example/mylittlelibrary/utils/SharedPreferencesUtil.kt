package com.example.mylittlelibrary.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast


object SharedPreferencesUtil {

    private const val PREF_KEY_TIME_TO_LIVE = "TimeInMilliSec"
    private const val PREF_FILE_NAME = "MyFile"

    fun updateTimeToLiveForBooks(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(
            PREF_FILE_NAME, Context.MODE_PRIVATE
        )
        // write all the data entered by the user in SharedPreference and apply
        Toast.makeText(context, System.currentTimeMillis().toString(), Toast.LENGTH_SHORT).show()
        val edit = prefs.edit()
        edit.putLong(PREF_KEY_TIME_TO_LIVE, System.currentTimeMillis())
        edit.apply()
    }

    fun getTimeToLiveFromPreferences(context: Context): Long {
        val prefs: SharedPreferences = context.getSharedPreferences(
            PREF_FILE_NAME, Context.MODE_PRIVATE
        )
        return prefs.getLong(PREF_KEY_TIME_TO_LIVE, 0)
    }
}