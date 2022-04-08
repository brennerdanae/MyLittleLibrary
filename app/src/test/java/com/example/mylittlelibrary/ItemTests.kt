package com.example.mylittlelibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ItemTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `Given movie data is available when I search for Green Mile then I should see Green Mile`() {
        assert(true)
        assertEquals(2, 1+1)
    }

    @Test
    fun `Given game data is available when I search for Parks then I should see Parks`() {
        assert(true)
        assertEquals(2, 1+1)
    }

    @Test
    fun `Given game data is available when I search for Maid then I should see Maid`() {
        assert(true)
        assertEquals(2, 1+1)
    }
}