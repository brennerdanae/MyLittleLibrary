package com.example.mylittlelibrary

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun threePlusThree_EqualsSix(){
        assertEquals(6, 3+3)
    }

    @Test
    fun threePlusFour_EqualsSeven(){
        assertEquals(7, 3+4)
    }

    @Test
    fun fourPlusFour_EqualsEight(){
        assertEquals(8, 4+4)
    }

    @Test
    fun confirmBookEntry_outputsBookEntry(){

    }

    @Test
    fun confirmMoviwEntry_outputsMovieEntry(){

    }

    @Test
    fun confirmGameEntry_outputsGameEntry(){

    }
}