package com.example.mylittlelibrary.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        setContentView(R.layout.activity_list)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this, ListActivity::class.java)

        if (intent.hasExtra("Clicked")){
            val itemSelected = intent.getStringExtra("Clicked")
            viewModel.fetchBooks()
            viewModel.books.observe(this) {
                Log.i(TAG, it.toString())
            }
        }

        binding.btnBack.setOnClickListener {
            intent.putExtra("Clicked", "Back")
            finish()
        }



    }

    companion object {
        const val TAG = "ListActivity"
    }
}