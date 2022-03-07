package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mylittlelibrary.R
import com.example.mylittlelibrary.ui.viewModel.BookViewModel

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val viewModel = BookViewModel()
        if (intent.hasExtra("Clicked")){
            val itemSelected = intent.getStringExtra("Clicked")
            viewModel.fetchBooks()
            viewModel.books.observe(this, Observer {
                Log.i(TAG, it.toString())
            })
            Toast.makeText(this, "$itemSelected Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val TAG = "ListActivity"
    }
}