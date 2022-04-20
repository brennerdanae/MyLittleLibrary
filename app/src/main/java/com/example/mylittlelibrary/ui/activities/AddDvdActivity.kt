package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.databinding.ActivityAddDvdBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import javax.inject.Inject
import kotlin.random.Random

class AddDvdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDvdBinding

    @Inject
    lateinit var addDvdViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddDvdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Add DVD"

        binding.btnSubmit.setOnClickListener {
            when {
                binding.editTextDvd.text.isEmpty() -> {
                    binding.editTextDvd.error = "Required"
                    return@setOnClickListener
                }
                binding.editTextDvdLendTo.text.isEmpty() -> {
                    binding.editTextDvdLendTo.error = "Required"
                    return@setOnClickListener
                }
                binding.editTextDvdDate.text.isEmpty() -> {
                    binding.editTextDvdDate.error = "Required"
                    return@setOnClickListener
                }
                else -> {
                    val num = Random.nextInt(0, 10000)
                    val dvd = Dvd(
                        id = num,
                        name = binding.editTextDvd.text.toString(),
                        lendTo = binding.editTextDvdLendTo.text.toString(),
                        date = binding.editTextDvdDate.text.toString()
                    )
                    addDvdViewModel.addDvd(dvd)
                }
            }
            addDvdViewModel.myResponse.observe(this) {
                if (it) {
                    addDvdViewModel.fetchDvds()
                    onBackPressed()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}