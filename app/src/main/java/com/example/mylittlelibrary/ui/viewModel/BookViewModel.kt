package com.example.mylittlelibrary.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.service.BookService
import kotlinx.coroutines.launch

class BookViewModel(var bookService: BookService = BookService()): ViewModel() {
    var books : MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    fun fetchBooks(){
        viewModelScope.launch {
            books.postValue(bookService.fetchBooks())
        }
    }
}