package test

import org.testng.annotations.Test
import src.Advent9
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Advent9Test {

    @Test
    fun getGridFromInputString() {
        val advent9 = Advent9()
        advent9.receiveInput(listOf("888", "848", "888"))
        assertEquals(listOf(listOf(8,8,8),listOf(8,4,8),listOf(8,8,8)), advent9.grid)
    }

    @Test
    fun getLowPointGivenSmallGrid() {
        val advent9 = Advent9()
        advent9.receiveInput(listOf("888", "848", "888"))
        advent9.findLowPoints()
        assertEquals(listOf(4), advent9.lowPoints)
    }

    @Test
    fun getBasin() {
        val advent9 = Advent9()
        advent9.receiveInput(listOf("888", "848", "888"))
        advent9.findLowPoints()
        advent9.findBiggestBasins()
        assertTrue(advent9.basin[0].containsAll(listOf("0:1","1:0","1:1","2:1","1:2")))
    }
}