package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mylittlelibrary.R
import com.example.mylittlelibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //testing

        binding.books.setOnClickListener {
            Toast.makeText(this, "Books Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.movies.setOnClickListener{
            Toast.makeText(this, "Movies Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.dvds.setOnClickListener {
            Toast.makeText(this, "DVDs Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}