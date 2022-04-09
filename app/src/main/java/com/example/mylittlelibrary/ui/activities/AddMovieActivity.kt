package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.databinding.ActivityAddMovieBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel

class AddMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    private val addMovieViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnMovieSubmit.setOnClickListener {
            val movie = Movie(
                id = Math.random().toInt(),
                name = binding.editTextMovie.text.toString(),
                lendTo = binding.editTextMovieLendTo.text.toString(),
                date = binding.editTextMovieDate.text.toString()
            )
            addMovieViewModel.addMovie(movie)
        }
        addMovieViewModel.myResponse.observe(this, Observer {
            if (it) {
                addMovieViewModel.fetchMovies()
                onBackPressed()
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}