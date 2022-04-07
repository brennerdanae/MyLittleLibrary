package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.R
import com.example.mylittlelibrary.databinding.ActivityListBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    @Inject
    lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("Clicked")){
            val itemSelected = intent.getStringExtra("Clicked")
            if(itemSelected == "Books"){
                viewModel.fetchBooks()
                viewModel.books.observe(this, Observer {
                    Log.i(TAG, it.toString())
                })
            } else if (itemSelected == "Movies"){
                binding.btnAddBook.text = "Add Movie"
                viewModel.fetchMovies()
                viewModel.movies.observe(this, Observer {
                    Log.i(TAG, it.toString())
                })
            } else {
                binding.btnAddBook.text = "Add Dvd"
                viewModel.fetchDvds()
                viewModel.dvds.observe(this, Observer {
                    Log.i(TAG, it.toString())
                })
            }
        }
    }

    companion object {
        const val TAG = "ListActivity"
    }
}