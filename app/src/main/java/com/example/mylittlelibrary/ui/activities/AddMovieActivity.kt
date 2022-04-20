package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Dvd
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}