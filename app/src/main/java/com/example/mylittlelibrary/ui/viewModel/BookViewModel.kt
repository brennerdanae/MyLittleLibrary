package com.example.mylittlelibrary.ui.viewModel

import androidx.lifecycle.*
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

    fun fetchBooks() {
        viewModelScope.launch {
            repository.fetchBooksFromDb(this@BookViewModel)
        }
    }

    fun addBook(book: Book) {
        viewModelScope.launch {
            //myResponse.postValue(repository.addBook(book))
            repository.addBook(book)
            myResponse.postValue(true)
        }
    }

    fun fetchMovies(){
        viewModelScope.launch {
            repository.fetchMovieFromDb(this@BookViewModel)
        }
    }

    fun addMovie(movie: Movie){
        viewModelScope.launch {
            repository.addMovie(movie)
            myResponse.postValue(true)
        }
    }

    fun fetchDvds(){
        viewModelScope.launch {
            repository.fetchDvdFromDb(this@BookViewModel)
        }
    }

    fun addDvd(dvd: Dvd){
        viewModelScope.launch {
            repository.addDvd(dvd)
            myResponse.postValue(true)
        }
    }

    override fun passBookToViewModel(books: Flow<List<Book>>) {
        this.books = books.asLiveData()
    }

    override fun passMovieToViewModel(movies: Flow<List<Movie>>) {
        this.movies = movies.asLiveData()
    }

    override fun passDvdToViewModel(dvd: Flow<List<Dvd>>) {
        this.dvds = dvd.asLiveData()
    }
}