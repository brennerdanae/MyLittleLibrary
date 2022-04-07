package com.example.mylittlelibrary.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.databinding.ActivityAddDvdBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel

class AddDvdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDvdBinding
    private val addDvdViewModel: BookViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddDvdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val dvd = Dvd(
                id = Math.random().toInt(),
                name = binding.editTextDvd.text.toString(),
                lendTo = binding.editTextDvdLendTo.text.toString(),
                date = binding.editTextDvdDate.text.toString()
            )
            addDvdViewModel.addDvd(dvd)
        }
        addDvdViewModel.myResponse.observe(this, Observer {
            if (it) {
                addDvdViewModel.fetchDvds()
                onBackPressed()
            }
        })
    }
}