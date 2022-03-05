package com.example.mylittlelibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.service.BookService
import com.example.mylittlelibrary.ui.viewModel.BookViewModel
import io.mockk.impl.annotations.MockK
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import kotlinx.coroutines.newSingleThreadContext
import org.junit.After

import org.junit.Before

class BookUnitTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var boookService: BookService
    lateinit var mvm: BookViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var mockBookService: BookService
    @Before
    fun populateBooks() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        mvm = BookViewModel()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `given a view model with live data when populated with countries then results should contain Belize`() {
        givenViewModelIsInitializedWithMockData()
        whenJSONDataAreReadAndParsed()
        thenResultsShouldContainHamlet()
    }

    private fun givenViewModelIsInitializedWithMockData() {
        val books = ArrayList<Book>()
        books.add(Book(1, "Hamlet","9780140707342"))

        coEvery {mockBookService.fetchBooks()} returns books

        mvm.bookService = mockBookService
    }

    private fun whenJSONDataAreReadAndParsed() {
        mvm.fetchBooks()
        var test= "test"
    }

    private fun thenResultsShouldContainHamlet() {
        var allBooks: List<Book>? = ArrayList<Book>()
        val latch = CountDownLatch(1);
        val observer = object : Observer<List<Book>> {
            override fun onChanged(t: List<Book>?) {
                allBooks = t
                latch.countDown()
                mvm.books.removeObserver(this)
            }
        }
        mvm.books.observeForever(observer)

        latch.await(1, TimeUnit.SECONDS)
        Assert.assertNotNull(allBooks)
        Assert.assertTrue(allBooks!!.contains(Book(1, "Hamlet","9780140707342")))

    }
}