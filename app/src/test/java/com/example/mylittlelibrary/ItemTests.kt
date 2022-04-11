package com.example.mylittlelibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Game
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.service.BookService
import com.example.mylittlelibrary.service.GameService
import com.example.mylittlelibrary.service.MovieService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ItemTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var bookService: BookService
    lateinit var movieService: MovieService
    lateinit var gameService: GameService

    var allBooks: List<Book>? = ArrayList<Book>()
    var allMovies: List<Movie>? = ArrayList<Movie>()
    var allGames: List<Game>? = ArrayList<Game>()

    @Test
    fun `Given movie data is available when I search for Inception then I should see Inception`() =
        runTest {
            givenMovieServiceIsInitialized()
            whenMovieDataAreReadAndParsed()
            thenTheMovieCollectionShouldContainInception()
            assert(true)
            assertEquals(2, 1 + 1)
        }


    @Test
    fun `Given game data is available when I search for Fortnite then I should see Fortnite`() =
        runTest {
            givenGameServiceIsInitialized()
            whenGameDataAreReadAndParsed()
            thenTheGameCollectionShouldContainFortnite()
            assert(true)
            assertEquals(2, 1 + 1)
        }


    @Test
    fun `Given book data is available when I search for Wuthering Heights then I should see Wuthering Heights`() =
        runTest {
            givenBookServiceIsInitialized()
            whenBookDataAreReadAndParsed()
            thenTheBookCollectionShouldContainWutheringHeights()
            assert(true)
            assertEquals(2, 1 + 1)
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

    private fun thenTheMovieCollectionShouldContainInception() {
        assertNotNull(allMovies)
        assertTrue(allMovies!!.isNotEmpty())
        var containsInception = false
        allMovies!!.forEach {
            if (it.name == ("Inception")) {
                containsInception = true
            }
        }
        assertTrue(containsInception)

    }

    private fun thenTheGameCollectionShouldContainFortnite() {
        assertNotNull(allGames)
        assertTrue(allGames!!.isNotEmpty())
        var containsFortnite = false
        allMovies!!.forEach {
            if (it.name == ("Fortnite")) {
                containsFortnite = true
            }
        }
        assertTrue(containsFortnite)
    }

    private fun thenTheBookCollectionShouldContainWutheringHeights() {
        assertNotNull(allBooks)
        assertTrue(allBooks!!.isNotEmpty())
        var containsWutheringHeights = false
        allMovies!!.forEach {
            if (it.name == ("Wuthering Heights")) {
                containsWutheringHeights = true
            }
        }
        assertTrue(containsWutheringHeights)
    }
}