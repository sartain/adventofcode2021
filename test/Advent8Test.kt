package test

import org.testng.annotations.Test
import src.Advent8
import kotlin.test.assertEquals

class Advent8Test {

    /*TODO:
        1 value = 2 values
        4 value = 4 values
        7 value = 3 values
        8 value = 7 values
     */

    @Test
    fun detectOneValue() {
        val advent8 = Advent8()
        advent8.loadInput(listOf("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cg cg cg cg"))
        assertEquals(4, advent8.detectDigits())
    }

    @Test
    fun detectFourValue() {
        val advent8 = Advent8()
        advent8.loadInput(listOf("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | c c fcge c"))
        assertEquals(1, advent8.detectDigits())
    }

    @Test
    fun detectSevenValue() {
        val advent8 = Advent8()
        advent8.loadInput(listOf("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | c c cde c"))
        assertEquals(1, advent8.detectDigits())
    }

    @Test
    fun detectEightValue() {
        val advent8 = Advent8()
        advent8.loadInput(listOf("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cdecdec c c c"))
        assertEquals(1, advent8.detectDigits())
    }

    @Test
    fun detectOneOfEach() {
        val advent8 = Advent8()
        advent8.loadInput(listOf("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cd cde cdec cdecdec"))
        assertEquals(4, advent8.detectDigits())
    }
}