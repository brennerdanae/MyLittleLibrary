package com.example.mylittlelibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Game
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.service.BookService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ItemTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var bookService : BookService
    lateinit var movieService : MovieService
    lateinit var gameService : GameService

    var allBooks : List<Book>? = ArrayList<Book>
    var allMovies : List<Movie>? = ArrayList<Movie>
    var allGames : List<Game>? = ArrayList<Game>

    @Test
    fun `Given movie data is available when I search for Green Mile then I should see Green Mile` () = runTest {
        givenMovieServiceIsInitialized()
        whenMovieDataAreReadAndParsed()
        thenTheMovieCollectionShouldContainGreenMile()
        assert(true)
        assertEquals(2, 1+1)
    }


    @Test
    fun `Given game data is available when I search for Parks then I should see Parks` () = runTest {
        givenGameServiceIsInitialized()
        whenGameDataAreReadAndParsed()
        thenTheGameCollectionShouldContainParks()
        assert(true)
        assertEquals(2, 1+1)
    }


    @Test
    fun `Given book data is available when I search for Maid then I should see Maid` () = runTest {
        givenBookServiceIsInitialized()
        whenBookDataAreReadAndParsed()
        thenTheBookCollectionShouldContainMaid()
        assert(true)
        assertEquals(2, 1+1)
    }


    private suspend fun whenBookDataAreReadAndParsed() {
        allBooks = bookService.fetchBooks()
    }

    private suspend fun whenGameDataAreReadAndParsed() {
        allGames = gameService.fetchGames()
    }

    private suspend fun whenMovieDataAreReadAndParsed() {
        allMovies = movieService.fetchMovies()
    }

    private fun givenBookServiceIsInitialized() {
        bookService = BookService()
    }

    private fun givenGameServiceIsInitialized() {
        gameService = GameService()
    }

    private fun givenMovieServiceIsInitialized() {
        movieService = MovieService()
    }

    private fun thenTheMovieCollectionShouldContainGreenMile() {
        assertNotNull(allMovies)
        assertTrue(allMovies!!.isNotEmpty())
        var containsGreenMile = false
        allMovies!!.forEach {
            if (it.name.equals(("Green Mile"))){
                containsGreenMile = true
            }
        }
        assertTrue(containsGreenMile)

    }

    private fun thenTheGameCollectionShouldContainParks() {
        assertNotNull(allGames)
        assertTrue(allGames!!.isNotEmpty())
        var containsParks = false
        allMovies!!.forEach {
            if (it.name.equals(("Parks"))){
                containsParks = true
            }
        }
        assertTrue(containsParks)
    }

    private fun thenTheBookCollectionShouldContainMaid() {
        assertNotNull(allBooks)
        assertTrue(allBooks!!.isNotEmpty())
        var containsMaid = false
        allMovies!!.forEach {
            if (it.name.equals(("Maid"))){
                containsMaid = true
            }
        }
        assertTrue(containsMaid)
    }
}