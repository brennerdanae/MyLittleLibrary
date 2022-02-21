package com.example.mylittlelibrary.ui.activities

import android.content.Intent
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

        var intent = Intent(this, ListActivity::class.java)

        binding.books.setOnClickListener {
            intent.putExtra("Clicked", "Books")
            startActivity(intent)
        }

        binding.movies.setOnClickListener{
            intent.putExtra("Clicked", "Movies")
            startActivity(intent)
        }

        binding.dvds.setOnClickListener {
            intent.putExtra("Clicked", "DVDs")
            startActivity(intent)
        }
    }
}