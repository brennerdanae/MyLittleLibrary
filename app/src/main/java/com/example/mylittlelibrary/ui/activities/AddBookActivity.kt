package com.example.mylittlelibrary.ui.activities

import android.os.Bundle
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
        title = "Add Book"


        binding.btnSubmit.setOnClickListener {
            // Input validation is done here to make sure user entered all the fields
            when {
                binding.editTextBook.text.isEmpty() -> {
                    binding.editTextBook.error = "Required"
                    return@setOnClickListener
                }
                binding.editTextLendTo.text.isEmpty() -> {
                    binding.editTextLendTo.error = "Required"
                    return@setOnClickListener
                }
                binding.editTextDate.text.isEmpty() -> {
                    binding.editTextDate.error = "Required"
                    return@setOnClickListener
                }
                else -> {
                    val num = Random.nextInt(0, 10000)
                    val book = Book(
                        id = num,
                        name = binding.editTextBook.text.toString(),
                        lendTo = binding.editTextLendTo.text.toString(),
                        date = binding.editTextDate.text.toString()
                    )
                    //add book to db using view model
                    addBookViewModel.addBook(book)
                }
            }
            addBookViewModel.myResponse.observe(this) {
                if (it) {
                    addBookViewModel.fetchBooks()
                    onBackPressed()
                }
            }
        }
    }
    // on screen back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
