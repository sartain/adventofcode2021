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
        advent10.fromInputReportErrors(listOf(listOf("<", ">", "{", "}", "[", "]", "(", ")")))
        assertEquals(0, advent10.score)
    }

    @Test
    fun invalidQueue() {
        val advent10 = Advent10()
        advent10.fromInputReportErrors(listOf(listOf("(", ">")))
        assertEquals(25137, advent10.score)
    }

    @Test
    fun stopAtOneError() {
        val advent10 = Advent10()
        advent10.fromInputReportErrors(listOf(listOf("<", ")", ")", "<", "}")))
        assertEquals(3, advent10.score)
    }

    @Test
    fun score57WhenSquare() {
        val advent10 = Advent10()
        advent10.fromInputReportErrors(listOf(listOf("(", "]", ")", "<", "}")))
        assertEquals(57, advent10.score)
    }

    @Test
    fun score1197WhenBrace() {
        val advent10 = Advent10()
        advent10.fromInputReportErrors(listOf(listOf("<", "}", ")", "<", "}")))
        assertEquals(1197, advent10.score)
    }

    @Test
    fun scoreIncompleteLine() {
        val advent10 = Advent10()
        advent10.fromInputFixIncomplete(listOf(listOf("<")))
        assertEquals(4, advent10.incomplete[0])
    }

    @Test
    fun scoreIncompleteLineMultiple() {
        val advent10 = Advent10()
        advent10.fromInputFixIncomplete(listOf(listOf("<", "<", "<")))
        assertEquals(124, advent10.incomplete[0])
    }
}