package com.example.mylittlelibrary.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.R
import com.example.mylittlelibrary.databinding.ActivityListBinding
import com.example.mylittlelibrary.ui.adapters.RecyclerViewAdapter
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: BookViewModel
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("Clicked")) {
            val recyclerViewAdapter = intent.getStringExtra("Clicked")
                ?.let { RecyclerViewAdapter(it) }
            binding.listItem.adapter = recyclerViewAdapter
            when (intent.getStringExtra("Clicked")) {
                "Books" -> {
                    viewModel.fetchBooks()
                    viewModel.books.observe(this, Observer {
                        Log.i(TAG, it.toString())
                        recyclerViewAdapter?.setFeedItemList(it, "book")
                    })
                }
                "Movies" -> {
                    binding.btnAdd.text = "Add Movie"
                    viewModel.fetchMovies()
                    viewModel.movies.observe(this, Observer {
                        Log.i(TAG, it.toString())
                        recyclerViewAdapter?.setFeedItemList(it, "movie")
                    })
                }
                else -> {
                    binding.btnAdd.text = "Add Dvd"
                    viewModel.fetchDvds()
                    viewModel.dvds.observe(this, Observer {
                        Log.i(TAG, it.toString())
                        recyclerViewAdapter?.setFeedItemList(it, "dvd")
                    })
                }
            }
            binding.btnAdd.setOnClickListener {
                if (intent.hasExtra("Clicked")) {
                    when (intent.getStringExtra("Clicked")) {
                        "Books" -> {
                            val intent = Intent(this, AddBookActivity::class.java)
                            startActivity(intent)
                        }

                        "Movies" -> {
                            val intent = Intent(this, AddMovieActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            val intent = Intent(this, AddDvdActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
    companion object {
        const val TAG = "ListActivity"
    }
}
