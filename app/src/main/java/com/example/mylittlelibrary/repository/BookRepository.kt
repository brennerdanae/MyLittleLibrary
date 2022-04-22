package com.example.mylittlelibrary.repository

import android.content.Context
import android.util.Log
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.room.BookRoomDatabase
import com.example.mylittlelibrary.service.BookService
import com.example.mylittlelibrary.utils.SharedPreferencesUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val context: Context,
    private val database: BookRoomDatabase,
    private val service: BookService
) {

    companion object {
        const val TIME_TO_LIVE_FOR_BOOKS = 15 * 60 * 1000
    }

    val allBooks: Flow<List<Book>> = database.bookDao().getAllBooks()
    val allMovie: Flow<List<Movie>> = database.movieDao().getAllMovies()
    val allDvd: Flow<List<Dvd>> = database.dvdDao().getAllDvd()

    // add functions to add book to db using bookDao.insert()
    suspend fun addBook(book: Book)= database.bookDao().insert(book)

    // add functions to add movie to db using movieDao.insert()
    suspend fun addMovie(movie: Movie) = database.movieDao().insertMovie(movie)

    // add functions to add dvd to db using dvdDao.insert()
    suspend fun addDvd(dvd: Dvd) = database.dvdDao().insertDvd(dvd)

    // fetching from service
    suspend fun fetchBooks() {
        Result.runCatching {
            service.fetchBooks()
        }.onSuccess { books ->
            books?.let {
                database.bookDao().insertAll(it)
            }
        }.onFailure {
            Log.d("Service Error", it.message.toString())
        }
    }

    // fetch from db first and if result is empty then calling service
    suspend fun fetchBooksFromDb(iListener: IListener) {
        val listOfBooks = database.bookDao().getAllBooks()
        Log.i("Books", listOfBooks.count().toString())
        if (listOfBooks.count() != 0 ) { //!= 0 && !checkForTTL()
            iListener.passBookToViewModel(listOfBooks)
        } else {
            runCatching {
                service.fetchBooks()
            }.onSuccess {
                if (it != null) {
                    SharedPreferencesUtil.updateTimeToLiveForBooks(context)
                    database.bookDao().insertAll(it)
                }
            }.onFailure { throwable ->
                Log.i("Error Message", throwable.message.toString())
            }
        }
    }

    // fetching from service
    suspend fun fetchMovies() {
        Result.runCatching {
            service.fetchMovies()
        }.onSuccess { movies ->
            movies?.let {
                database.movieDao().insertAll(it)
            }
        }.onFailure { throwable ->
            Log.d("Service Error", throwable.message.toString())
        }
    }

    // fetch from db first and if result is empty then calling service
    suspend fun fetchMovieFromDb(iListener: IListener){
        val listOfMovie = database.movieDao().getAllMovies()
        if (listOfMovie.count() != 0 ) { //!= 0 && !checkForTTL()
            iListener.passMovieToViewModel(listOfMovie)
        } else {
            runCatching {
                service.fetchMovies()
            }.onSuccess {
                if (it != null) {
                    SharedPreferencesUtil.updateTimeToLiveForBooks(context)
                    database.movieDao().insertAll(it)
                }
            }.onFailure { throwable ->
                Log.i("Error Message", throwable.message.toString())
            }
        }
    }

    // fetching from service
    suspend fun fetchDvd() {
        Result.runCatching {
            service.fetchDvd()
        }.onSuccess { dvds ->
            dvds?.let {
                database.dvdDao().insertAll(dvds)
            }
        }.onFailure { throwable ->
            Log.d("Service Error", throwable.message.toString())
        }
    }

    // fetch from db first and if result is empty then calling service
    suspend fun fetchDvdFromDb(iListener: IListener){
        val listOfDvd = database.dvdDao().getAllDvd()
        if (listOfDvd.count() != 0 ) { //!= 0 && !checkForTTL()
            iListener.passDvdToViewModel(listOfDvd)
        } else {
            runCatching {
                service.fetchDvd()
            }.onSuccess {
                if (it != null) {
                    SharedPreferencesUtil.updateTimeToLiveForBooks(context)
                    database.dvdDao().insertAll(it)
                }
            }.onFailure { throwable ->
                Log.i("Error Message", throwable.message.toString())
            }
        }
    }

    private fun checkForTTL(): Boolean {
        return (System.currentTimeMillis() - SharedPreferencesUtil.getTimeToLiveFromPreferences(context)) > TIME_TO_LIVE_FOR_BOOKS
    }

    // interface to pass data retrieved from db to view model
    interface IListener {
        fun passBookToViewModel(books: Flow<List<Book>>)
        fun passMovieToViewModel(movies: Flow<List<Movie>>)
        fun passDvdToViewModel(dvd: Flow<List<Dvd>>)
    }
}