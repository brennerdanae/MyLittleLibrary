package com.example.mylittlelibrary.ui.activities

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.MyLittleLibraryApplication
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Photo
import com.example.mylittlelibrary.databinding.ActivityAddBookBinding
import com.example.mylittlelibrary.ui.activities.ListActivity.Companion.TAG
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddBookActivity : AppCompatActivity() {
    private var uri: Uri? = null
    private lateinit var currentImagePath: String
    private lateinit var binding: ActivityAddBookBinding
    private val addBookViewModel: BookViewModel by viewModels()
    private var strUri by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyLittleLibraryApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        //working on trying to integrate camera. I think we need this line within a composable
        //AsyncImage(model = strUri, contentDescription= "Specimen Image")
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
        val file = createImageFile()
        try {
            uri = FileProvider.getUriForFile(this, "com.example.mylittlelibrary.fileprovider", file)
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            var foo = e.message
        }
        getCameraImage.launch(uri)
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "Specimen_${timestamp}",
            ".jpg",
            imageDirectory
        ).apply {
            currentImagePath = absolutePath
        }
    }

    private val getCameraImage =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Log.i(TAG, "Image Location: $uri")
                strUri = uri.toString()
                val photo = Photo(localUri= uri.toString())
                //I think we need to move the camera to a page that used the view Model
                //viewModel.photos.add(photo)

            } else {
                Log.e(TAG, "Image not saved. $uri")
            }

        }

    fun hasCameraPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    fun hasExternalStoragePermission() =
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}