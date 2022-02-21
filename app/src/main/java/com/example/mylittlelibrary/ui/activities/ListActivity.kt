package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mylittlelibrary.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        if (intent.hasExtra("Clicked")){
            val itemSelected = intent.getStringExtra("Clicked")
            Toast.makeText(this, "$itemSelected Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}