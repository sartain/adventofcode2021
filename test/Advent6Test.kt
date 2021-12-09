package test

import org.testng.annotations.Test
import src.Advent6
import kotlin.test.assertEquals

class Advent6Test {

    @Test
    fun givenDayIncreaseReduceFishCounter() {
        val advent6 = Advent6()
        advent6.addFishGivenString("3,4,5")
        advent6.simulateDays(1)
        assertEquals(2, advent6.fish[0])
    }

    @Test
    fun addFishGivenCounterDropsToZero() {
        val advent6 = Advent6()
        advent6.addFishGivenString("1")
        advent6.simulateDays(1)
        assertEquals(8, advent6.fish[1])
    }

}