package test

import org.testng.annotations.Test
import src.Advent13
import kotlin.test.assertEquals

class Advent13Test {

    @Test
    fun createDotArrayGivenInput() {
        val advent13 = Advent13()
        advent13.convertInputToGridAndInstructions(listOf("2,1"))
        assertEquals(listOf(listOf(".", ".", "."), listOf(".", ".", "#")), advent13.wrapArray)
    }

    @Test
    fun foldArrayX() {
        val advent13 = Advent13()
        advent13.convertInputToGridAndInstructions(listOf("2,1", "fold along x=1"))
        advent13.followInstructionsUpTo(1)
        assertEquals(listOf(listOf("."), listOf("#")), advent13.wrapArray)
    }

    @Test
    fun foldArrayY() {
        val advent13 = Advent13()
        advent13.convertInputToGridAndInstructions(listOf("1,2", "fold along y=1"))
        advent13.followInstructionsUpTo(1)
        assertEquals(listOf(listOf(".","#")), advent13.wrapArray)
    }
}