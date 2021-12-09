package test

import org.testng.annotations.Test
import src.Advent7
import kotlin.test.assertEquals

class Advent7Test {
    @Test
    fun minimumFuelCostValueTwoValues() {
        val advent7 = Advent7()
        advent7.convertInputToCrabList("2,1")
        var minimumFuelCostValue = advent7.realignCrabs()
        assertEquals(1, minimumFuelCostValue)
    }

    @Test
    fun minimumFuelCost() {
        val advent7 = Advent7()
        advent7.convertInputToCrabList("16,1,2,0,4,2,7,1,2,14")
        var minimumFuelCostValue = advent7.realignCrabs()
        assertEquals(37, minimumFuelCostValue)
    }
}