package test
import org.testng.annotations.Test
import src.Advent2
import kotlin.test.assertEquals
class Advent2Test {

    @Test
    fun moveUpIncreaseHorizontal() {
        val advent2 = Advent2(0, 0)
        advent2.processCommands(listOf("forward 2"))
        assertEquals(2, advent2.xPos)
    }

}