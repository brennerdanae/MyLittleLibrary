package com.example.mylittlelibrary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.databinding.BookBinding
import com.example.mylittlelibrary.databinding.DvdBinding
import com.example.mylittlelibrary.databinding.MovieBinding

class RecyclerViewAdapter(private var type: String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var bookList: List<Book> = emptyList()
    private var movieList: List<Movie> = emptyList()
    private var dvdList: List<Dvd> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            "book" -> BookViewHolder(
                BookBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            )
            "movie" -> MovieViewHolder(
                MovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> DvdViewHolder(
                DvdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = bookList[position]
        when (type) {
            "book" -> {
                val item = bookList[position]
                (holder as BookViewHolder).binding.textViewBookName.text = item.name
                (holder).binding.textViewReturn.text = item.date
                (holder).binding.textViewLendTo.text = item.lendTo
            }
            "movie" -> {
                val item = movieList[position]
                (holder as MovieViewHolder).binding.textViewBookName.text = item.name
                (holder).binding.textViewReturn.text = item.date
                (holder).binding.textViewLendTo.text = item.lendTo
            }
            else -> {
                val item = dvdList[position]
                (holder as DvdViewHolder).binding.textViewBookName.text = item.name
                holder.binding.textViewReturn.text = item.date
                holder.binding.textViewLendTo.text = item.lendTo
            }
        }
    }

    override fun getItemCount(): Int {
        when(type) {
            "book" -> return bookList.size
            "movie" -> return movieList.size
            "dvd" -> return dvdList.size
        }
        return 0
    }

    fun setFeedItemList(feedItem: List<Any>, type: String) {
        this.type = type
        when(type) {
            "book" -> bookList = feedItem as List<Book>
            "movie" -> movieList = feedItem as List<Movie>
            "dvd" -> dvdList = feedItem as List<Dvd>
        }
        notifyDataSetChanged()
    }

    class BookViewHolder(val binding: BookBinding) :
        RecyclerView.ViewHolder(binding.root)

    class MovieViewHolder(val binding: MovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DvdViewHolder(val binding: DvdBinding) :
        RecyclerView.ViewHolder(binding.root)
}