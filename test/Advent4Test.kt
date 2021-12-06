package test

import org.testng.annotations.Test
import src.Advent4
import kotlin.test.assertEquals

class Advent4Test {

    @Test
    fun getRowsFromBoard() {
        var advent4 = Advent4()
        advent4.addBoard(listOf(listOf(3, 2, 1), listOf(3, 2, 1), listOf(3, 2, 1)))
        assertEquals(listOf(listOf(3, 2, 1), listOf(3, 2, 1), listOf(3, 2, 1)), advent4.rows)
    }

    @Test
    fun getColsFromBoard() {
        var advent4 = Advent4()
        advent4.addBoard(listOf(listOf(3, 2, 1), listOf(3, 2, 1), listOf(3, 2, 1)))
        assertEquals(listOf(listOf(3, 3, 3), listOf(2, 2, 2), listOf(1, 1, 1)), advent4.cols)
    }

    @Test
    fun checkRowIsAcceptable() {
        var advent4 = Advent4()
        advent4.addBoard(listOf(listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1)))
        advent4.addBoard(listOf(listOf(4, 2, 1, 1, 1), listOf(5, 2, 1, 1, 1), listOf(6, 2, 1, 1, 1), listOf(5, 2, 1, 1, 1), listOf(6, 2, 1, 1, 1)))
        advent4.addInput(listOf(4, 2, 1, 1, 1))
        assertEquals(1, advent4.winningBlock)
    }

    @Test
    fun checkColIsAcceptable() {
        var advent4 = Advent4()
        advent4.addBoard(listOf(listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1), listOf(3, 2, 1, 1, 1)))
        advent4.addBoard(listOf(listOf(4, 2, 1, 1, 1), listOf(5, 2, 1, 1, 1), listOf(6, 2, 1, 1, 1), listOf(5, 2, 1, 1, 1), listOf(6, 2, 1, 1, 1)))
        advent4.addInput(listOf(4, 5, 6, 5, 6))
        assertEquals(1, advent4.winningBlock)
    }

}