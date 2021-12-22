package test

import org.testng.annotations.Test
import src.Advent11
import kotlin.test.assertEquals

class Advent11Test {

    @Test
    fun addOneEnergyInOneStep() {
        var advent11 = Advent11()
        advent11.receiveListInput(listOf("1111","1111"))
        advent11.runSteps(1)
        assertEquals(mutableListOf(mutableListOf(2,2,2,2), mutableListOf(2,2,2,2)), advent11.flashList)
    }

    @Test
    fun blinkInOneStep() {
        var advent11 = Advent11()
        advent11.receiveListInput(listOf("1911","1111"))
        advent11.runSteps(1)
        assertEquals(mutableListOf(mutableListOf(3,0,3,2), mutableListOf(3,3,3,2)), advent11.flashList)
    }
}