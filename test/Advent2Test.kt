package test
import org.testng.annotations.Test
import src.Advent2
import kotlin.test.assertEquals
class Advent2Test {

    @Test
    fun moveForwardIncreaseXPos() {
        val advent2 = Advent2(0, 0)
        advent2.processCommands(listOf("forward 2"))
        assertEquals(2, advent2.xPos)
    }

    @Test
    fun moveUpIncreaseYPos() {
        val advent2 = Advent2(0, 0)
        advent2.processCommands(listOf("up 2"))
        assertEquals(2, advent2.yPos)
    }

    @Test
    fun moveDownDecreaseYPos() {
        val advent2 = Advent2(0, 0)
        advent2.processCommands(listOf("up 2", "down 2"))
        assertEquals(0, advent2.yPos)
    }

    @Test
    fun moveDownIncreaseAim() {
        val advent2 = Advent2(0, 0)
        advent2.processCommandsWithAim(listOf("down 5"))
        assertEquals(5, advent2.aim)
    }

    @Test
    fun moveUpDecreaseAim() {
        val advent2 = Advent2(0, 0)
        advent2.processCommandsWithAim(listOf("down 5", "up 3"))
        assertEquals(2, advent2.aim)
    }

    @Test
    fun moveForwardUpdateHorizontal() {
        val advent2 = Advent2(0, 0)
        advent2.processCommandsWithAim(listOf("forward 2"))
        assertEquals(2, advent2.xPos)
    }

    @Test
    fun moveForwardMultiplyDepthByAimAndValue() {
        val advent2 = Advent2(0, 0)
        advent2.processCommandsWithAim(listOf("forward 2"))
        assertEquals(0, advent2.yPos)
    }

    @Test
    fun givenAimMultiplyDepthByAimAndValue() {
        val advent2 = Advent2(0, 0)
        advent2.processCommandsWithAim(listOf("down 5", "forward 8"))
        assertEquals(40, advent2.yPos)
    }
}