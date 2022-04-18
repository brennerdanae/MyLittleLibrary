package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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

        binding.btnMovieSubmit.setOnClickListener {
            val num = Random.nextInt(0, 10000)
            Toast.makeText(this, num.toString(), Toast.LENGTH_SHORT).show()
            val movie = Movie(
                id = num,
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
}