package test

import org.testng.annotations.Test
import src.Advent14
import kotlin.test.assertEquals

class Advent14Test {
    @Test
    fun createInitialPairs() {
        val advent14 = Advent14()
        advent14.receiveInput(listOf("NNCB"))
        assertEquals(advent14.splitPairs, listOf("NN", "NC", "CB"))
    }

    @Test
    fun createPairMappings() {
        val advent14 = Advent14()
        advent14.receiveInput(listOf("NNCB", "CH -> B", "HH -> N"))
        assertEquals(advent14.pairMappings, listOf(Pair("CH", "B"), Pair("HH", "N")))
    }

}
/*
Plan
-Split original into pairs
-For each pair complete the rotation x times
-Use actual pair type for mapping in kotlin
-Split into pairs
-For each pair complete the rotation x-1 times etc etc etc
-Each time using a dict to update count of each character
 */

