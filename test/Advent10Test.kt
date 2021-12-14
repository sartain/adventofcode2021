package test

import org.testng.annotations.Test
import src.Advent10
import kotlin.test.assertEquals

class Advent10Test {

    //stop at first incorrect character on each line
    //start with ({[< end with such
    //Should use a stack
    //De-que in kotlin
    //Stop at first value incorrect in line
    //If doesnt close/stop ignore

    @Test
    fun acceptValidQueue() {
        val advent10 = Advent10()
        advent10.fromInputReportErrors(listOf(listOf("<",">","{","}","[","]","(",")")))
        assertEquals(0, advent10.score)
    }
}