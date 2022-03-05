package com.example.mylittlelibrary.ui.viewModel

import com.example.mylittlelibrary.service.BookService
import com.example.mylittlelibrary.service.IBookService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {
    viewModel { BookViewModel(get()) }
    single <IBookService>{ BookService() }
}