package test

import org.testng.annotations.Test
import src.Advent5
import kotlin.test.assertEquals

class Advent5Test {

    @Test
    fun detectOverlap() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        assertEquals(1, advent5.grid.get("0:0"))
    }

    @Test
    fun detectMultipleOverlaps() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        assertEquals(3, advent5.grid.get("0:0"))
    }

    @Test
    fun countNumberOfOverlaps() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        advent5.markSquaresGivenInput("0,0 -> 0,5", false)
        assertEquals(3, advent5.grid.get("0:0"))
    }

    @Test
    fun testDiagonal() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("9,7 -> 7,9", true)
        assertEquals(1, advent5.grid.get("8:8"))
        assertEquals(1, advent5.grid.get("9:7"))
        assertEquals(1, advent5.grid.get("7:9"))
        advent5.markSquaresGivenInput("7,9 -> 9,7", true)
        assertEquals(2, advent5.grid.get("8:8"))
        assertEquals(2, advent5.grid.get("9:7"))
        assertEquals(2, advent5.grid.get("7:9"))
        advent5.markSquaresGivenInput("7,9 -> 4,9", false)
        assertEquals(3, advent5.grid.get("7:9"))
    }

    @Test
    fun testDiagonalv2() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("1,1 -> 3,3", true)
        assertEquals(1, advent5.grid.get("1:1"))
        assertEquals(1, advent5.grid.get("2:2"))
        assertEquals(1, advent5.grid.get("3:3"))
        advent5.markSquaresGivenInput("3,3 -> 1,1", true)
        assertEquals(2, advent5.grid.get("1:1"))
        assertEquals(2, advent5.grid.get("2:2"))
        assertEquals(2, advent5.grid.get("3:3"))
    }

    @Test
    fun testDiagonalv3() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("0,0 -> 8,8", true)
        assertEquals(1, advent5.grid.get("0:0"))
        assertEquals(1, advent5.grid.get("1:1"))
        assertEquals(1, advent5.grid.get("2:2"))
        assertEquals(1, advent5.grid.get("3:3"))
        assertEquals(1, advent5.grid.get("4:4"))
        assertEquals(1, advent5.grid.get("5:5"))
        assertEquals(1, advent5.grid.get("6:6"))
        assertEquals(1, advent5.grid.get("7:7"))
        assertEquals(1, advent5.grid.get("8:8"))
    }

    @Test
    fun testDiagonalv4() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("3,7 -> 5,5", true)
        assertEquals(1, advent5.grid.get("5:5"))
        assertEquals(1, advent5.grid.get("4:6"))
        assertEquals(1, advent5.grid.get("3:7"))
        advent5.markSquaresGivenInput("5,5 -> 3,7", true)
        assertEquals(2, advent5.grid.get("5:5"))
        assertEquals(2, advent5.grid.get("4:6"))
        assertEquals(2, advent5.grid.get("3:7"))
    }

    @Test
    fun testDiagonalv40() {
        val advent5 = Advent5()
        advent5.markSquaresGivenInput("5,5 -> 7,3", true)
        assertEquals(1, advent5.grid.get("5:5"))
        assertEquals(1, advent5.grid.get("6:4"))
        assertEquals(1, advent5.grid.get("7:3"))
        advent5.markSquaresGivenInput("5,5 -> 7,3", true)
        assertEquals(2, advent5.grid.get("5:5"))
        assertEquals(2, advent5.grid.get("6:4"))
        assertEquals(2, advent5.grid.get("7:3"))
    }

}