package test

import org.testng.annotations.Test
import src.Advent12
import kotlin.test.assertEquals

class Advent12Test {

    @Test
    fun getOptionsFromStart() {
        val advent12 = Advent12()
        advent12.receiveInput(listOf("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"))
        assertEquals(listOf("A", "b"), advent12.getCaveOptionsGivenLocation("start"))
    }

    @Test
    fun getOptionsFromStartReverse() {
        val advent12 = Advent12()
        advent12.receiveInput(listOf("A-start", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"))
        assertEquals(listOf("A", "b"), advent12.getCaveOptionsGivenLocation("start"))
    }

    @Test
    fun caveOptionsSizeAsInstructions() {
        val advent12 = Advent12()
        advent12.receiveInput(listOf("A-start", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"))
        assertEquals(7, advent12.caveList.size)
    }

    /*@Test
    fun markSmallCaveVisited() {
        val advent12 = Advent12()
        advent12.receiveInput(listOf("A-start", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"))
        advent12.navigateCave("b")
        assertEquals(listOf("b"), advent12.smallCavesVisited)
    }
    No longer need test
    */

    @Test
    fun canNavigateCave() {
        val advent12 = Advent12()
        advent12.receiveInput(listOf("A-start", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"))
        advent12.navigateCaveStartToEnd()
        assert(advent12.navigationList.isNotEmpty())
    }

    @Test
    fun navigateCaveCorrectAmountOfTimes() {
        val advent12 = Advent12()
        advent12.receiveInput(listOf("A-start", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end"))
        advent12.navigateCaveStartToEnd()
        assertEquals(10, advent12.navigationList.size)
    }
}