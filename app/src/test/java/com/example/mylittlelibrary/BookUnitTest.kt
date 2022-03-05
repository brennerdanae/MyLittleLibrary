package com.example.mylittlelibrary

import com.example.mylittlelibrary.data.Book
import org.junit.Assert
import org.junit.Test
import java.util.*

class BookUnitTest {
    @Test
    fun `given a book dto when name is MyLittleLibrary and lendTo is MyLittleLibraryI then code is MyLittleLibrary and name is MyLittleLibraryI`() {
        var country = Book("MyLittleLibrary", "MyLittleLibraryI", Date())
        Assert.assertTrue(country.name.equals("MyLittleLibrary"))
        Assert.assertTrue(country.lendTo.equals("MyLittleLibraryI"))
    }
}