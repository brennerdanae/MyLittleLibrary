package com.example.mylittlelibrary.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel(), BookRepository.IListener {

    var books = repository.allBooks.asLiveData()
    var movies = repository.allMovie.asLiveData()
    var dvds = repository.allDvd.asLiveData()

    var myResponse: MutableLiveData<Boolean> = MutableLiveData()

    // Function to send request to repository to retrieve books from db
    fun fetchBooks() {
        viewModelScope.launch {
            repository.fetchBooksFromDb(this@BookViewModel)
        }
    }

    // adding book to db using the repository's addbook() function
    fun addBook(book: Book) {
        viewModelScope.launch {
            //myResponse.postValue(repository.addBook(book))
            repository.addBook(book)
            myResponse.postValue(true)
        }
    }

    // Function to send request to repository to retrieve movies from db
    fun fetchMovies(){
        viewModelScope.launch {
            repository.fetchMovieFromDb(this@BookViewModel)
        }
    }

    // adding book to db using the repository's addMovie() function
    fun addMovie(movie: Movie){
        viewModelScope.launch {
            repository.addMovie(movie)
            myResponse.postValue(true)
        }
    }

    // Function to send request to repository to retrieve dvds from db
    fun fetchDvds(){
        viewModelScope.launch {
            repository.fetchDvdFromDb(this@BookViewModel)
        }
    }

    // adding book to db using the repository's addDvd() function
    fun addDvd(dvd: Dvd){
        viewModelScope.launch {
            repository.addDvd(dvd)
            myResponse.postValue(true)
        }
    }

    // interface's function implementation to receive books from repository class
    override fun passBookToViewModel(books: Flow<List<Book>>) {
        this.books = books.asLiveData()
    }

    // interface's function implementation to receive movies from repository class
    override fun passMovieToViewModel(movies: Flow<List<Movie>>) {
        this.movies = movies.asLiveData()
    }

    // interface's function implementation to receive dvds from repository class
    override fun passDvdToViewModel(dvd: Flow<List<Dvd>>) {
        this.dvds = dvd.asLiveData()
    }
}