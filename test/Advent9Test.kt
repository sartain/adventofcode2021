package test

import org.testng.annotations.Test
import src.Advent9
import kotlin.test.assertEquals

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
}