package com.example.mylittlelibrary.ui.viewModel

import androidx.lifecycle.*
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.repository.BookRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    var books = repository.allBooks.asLiveData()
    var myResponse: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchBooks() {
        viewModelScope.launch {
            repository.fetchBooks()
        }
    }

    fun addBook(book: Book) {
        viewModelScope.launch {
            myResponse.postValue(repository.addBook(book))
        }
    }
}