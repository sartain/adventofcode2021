package test

import org.testng.annotations.Test
import src.Advent13
import kotlin.test.assertEquals

class Advent13Test {

    @Test
    fun createDotArrayGivenInput() {
        val advent13 = Advent13()
        advent13.receiveInput(listOf("2,1"))
        assertEquals(listOf(listOf(".", ".", "."), listOf(".", ".", "#")), advent13.wrapArray)
    }
}