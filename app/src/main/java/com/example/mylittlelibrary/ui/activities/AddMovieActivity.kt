package com.example.mylittlelibrary.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.databinding.ActivityAddMovieBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import javax.inject.Inject
import kotlin.random.Random

class AddMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding

    @Inject
    lateinit var addMovieViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Add Movie"

        binding.btnMovieSubmit.setOnClickListener {
            // Input validation is done here to make sure user entered all the fields
            when {
                binding.editTextMovie.text.isEmpty() -> {
                    binding.editTextMovie.error = "Required"
                    return@setOnClickListener
                }
                binding.editTextMovieLendTo.text.isEmpty() -> {
                    binding.editTextMovieLendTo.error = "Required"
                    return@setOnClickListener
                }
                binding.editTextMovieDate.text.isEmpty() -> {
                    binding.editTextMovieDate.error = "Required"
                    return@setOnClickListener
                }
                else -> {
                    val num = Random.nextInt(0, 10000)
                    val movie = Movie(
                        id = num,
                        name = binding.editTextMovie.text.toString(),
                        lendTo = binding.editTextMovieLendTo.text.toString(),
                        date = binding.editTextMovieDate.text.toString()
                    )
                    //add movie to db using view model
                    addMovieViewModel.addMovie(movie)
                }
            }

        }
        addMovieViewModel.myResponse.observe(this, Observer {
            if (it) {
                addMovieViewModel.fetchMovies()
                onBackPressed()
            }
        })
    }
    // on screen back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}