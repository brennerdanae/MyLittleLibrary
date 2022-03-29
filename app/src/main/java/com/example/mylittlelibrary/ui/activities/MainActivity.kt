package com.example.mylittlelibrary.ui.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mylittlelibrary.R
import com.example.mylittlelibrary.databinding.ActivityMainBinding
import com.google.rpc.LocalizedMessage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var imageView: ImageView
    lateinit var button: Button
    val REQUEST_IMAGE_CAPTURE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // testing

        var intent = Intent(this, ListActivity::class.java)

        binding.books.setOnClickListener {
            intent.putExtra("Clicked", "Books")
            startActivity(intent)
        }

        binding.movies.setOnClickListener{
            intent.putExtra("Clicked", "Movies")
            startActivity(intent)
        }

        binding.dvds.setOnClickListener {
            intent.putExtra("Clicked", "DVDs")
            startActivity(intent)

        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.btnAddBook)

        button.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e:ActivityNotFoundException){
                // Toast.makeText(this,"Error: " + e.LocalizedMessage,Toast.LENGTH_SHORT).show()
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(imageBitmap)
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
        }
    }
}