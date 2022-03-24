package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.R
import com.example.mylittlelibrary.ui.viewModel.BookViewModel

class ListActivity : AppCompatActivity() {

    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        if (intent.hasExtra("Clicked")){
            val itemSelected = intent.getStringExtra("Clicked")
            viewModel.fetchBooks()
            viewModel.books.observe(this, Observer {
                Log.i(TAG, it.toString())
            })
        }
    }

    companion object {
        const val TAG = "ListActivity"
    }
}