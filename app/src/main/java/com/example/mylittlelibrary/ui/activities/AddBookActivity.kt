package com.example.mylittlelibrary.ui.activities

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.databinding.ActivityAddBookBinding
import com.example.mylittlelibrary.ui.viewModel.BookViewModel

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding
    private val addBookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener {
            takePhoto()
            val book = Book(
                id = Math.random().toInt(),
                name = binding.editTextBook.text.toString(),
                lendTo = binding.editTextLendTo.text.toString(),
                date = binding.editTextDate.text.toString()
            )
            addBookViewModel.addBook(book)
        }
        addBookViewModel.myResponse.observe(this, Observer {
            if (it) {
                addBookViewModel.fetchBooks()
                onBackPressed()
            }
        })
    }

    private fun takePhoto() {
        if (hasCameraPermission() == PERMISSION_GRANTED && hasExternalStoragePermission() == PERMISSION_GRANTED) {
            // The user has already granted permissions for these activities. Toggle the camera!
            invokeCamera()
        } else {
            // The user has not granted permission, so we must request
            requestMultiplePermissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            )
        }
    }

    private val requestMultiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultsMap ->
            var permissionsGranted = false
            resultsMap.forEach {
                if (it.value) {
                    permissionsGranted = it.value
                } else {
                    permissionsGranted = false
                    return@forEach
                }
            }
            if (permissionsGranted) {
                invokeCamera()
            } else {
                Toast.makeText(this, "Unable to load camera without permission", Toast.LENGTH_LONG)
                    .show()
            }
        }

    private fun invokeCamera() {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun hasCameraPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    fun hasExternalStoragePermission() =
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}