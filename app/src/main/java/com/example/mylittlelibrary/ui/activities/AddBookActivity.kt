package com.example.mylittlelibrary.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.databinding.ActivityAddBookBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import javax.inject.Inject
import kotlin.random.Random

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding

    @Inject
    lateinit var addBookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener {
            val num = Random.nextInt(0, 10000)
            val book = Book(
                id = num,
                name = binding.editTextBook.text.toString(),
                lendTo = binding.editTextLendTo.text.toString(),
                date = binding.editTextDate.text.toString()
            )
            addBookViewModel.addBook(book)
        }
        addBookViewModel.myResponse.observe(this) {
            if (it) {
                addBookViewModel.fetchBooks()
                onBackPressed()
            }
        }
    }
}