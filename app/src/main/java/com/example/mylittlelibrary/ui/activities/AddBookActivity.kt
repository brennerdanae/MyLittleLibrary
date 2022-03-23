package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.databinding.ActivityAddBookBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding
    private val addBookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val book = Book(
                id = Math.random().toInt(),
                name = binding.editTextBook.text.toString(),
                lendTo = binding.editTextLendTo.text.toString(),
                date = binding.editTextDate.text.toString()
            )
            addBookViewModel.addBook(book)
        }
        addBookViewModel.myResponse.observe(this, Observer {
            if (it) {
                addBookViewModel.fetchBooks()
                onBackPressed()
            }
        })
    }
}