package com.example.mylittlelibrary.ui.activities

import android.content.Intent
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

    @Inject
    lateinit var viewModel: BookViewModel
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("Clicked")){
            val itemSelected = intent.getStringExtra("Clicked")
            if(itemSelected == "Books"){
                binding.btnAddItem.text = "Add Book"
                viewModel.fetchBooks()
                viewModel.books.observe(this, Observer {
                    Log.i(TAG, it.toString())
                })
            } else if (itemSelected == "Movies"){
                binding.btnAddItem.text = "Add Movie"
                viewModel.fetchMovies()
                viewModel.movies.observe(this, Observer {
                    Log.i(TAG, it.toString())
                })
            } else {
                binding.btnAddItem.text = "Add Dvd"
                viewModel.fetchDvds()
                viewModel.dvds.observe(this, Observer {
                    Log.i(TAG, it.toString())
                })
            }
        }

        binding.btnBack.setOnClickListener {
            intent.putExtra("Clicked", "Back")
            finish()
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val TAG = "ListActivity"
    }
}