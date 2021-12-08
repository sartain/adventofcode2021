package test

import org.testng.annotations.Test
import src.Advent5
import kotlin.test.assertEquals

class Advent5Test {

    @Test
    fun detectOverlap() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        assertEquals(1, advent5.grid.get("00"))
    }

    @Test
    fun detectMultipleOverlaps() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        assertEquals(3, advent5.grid.get("00"))
    }

    @Test
    fun countNumberOfOverlaps() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        advent5.markSquaresGivenInput("0,0 -> 0,5")
        assertEquals(3, advent5.grid.get("00"))
    }

}