package test

import org.testng.annotations.Test
import src.Advent3
import kotlin.test.assertEquals

class Advent3Test {

    @Test
    fun calculateGammaBit() {
        val adv3 = Advent3()
        adv3.calculateGammaEpsilon(listOf("11", "11"))
        assertEquals("11", adv3.gamma)
    }

    @Test
    fun calculateEpsilonBit() {
        val adv3 = Advent3()
        adv3.calculateGammaEpsilon(listOf("11", "11"))
        assertEquals("00", adv3.epsilon)
    }

    @Test
    fun calculateMixedGammaBit() {
        val adv3 = Advent3()
        adv3.calculateGammaEpsilon(listOf("10", "10"))
        assertEquals("10", adv3.gamma)
    }

    @Test
    fun multiplyGammaAndEpsilon() {
        val adv3 = Advent3()
        adv3.calculateGammaEpsilon(listOf("10", "10"))
        assertEquals(2, adv3.multiplyGammaEpsilon())
    }

}