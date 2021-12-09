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

    @Test
    fun determineNumberPattern() {
        val advent8 = Advent8()
        advent8.loadInput(listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"))
        val mapping : List<List<String>> = advent8.mapConnections("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
        assertEquals(listOf("a","b","c","d","e","g"), mapping[0])
        assertEquals(listOf("a","b"), mapping[1])
        assertEquals(listOf("a","c","d","f","g"), mapping[2])
        assertEquals(listOf("a","b","c","d","f"), mapping[3])
        assertEquals(listOf("a","b","e","f"), mapping[4])
        assertEquals(listOf("b","c","d","e","f"), mapping[5])
        assertEquals(listOf("b","c","d","e","f","g"), mapping[6])
        assertEquals(listOf("a","b","d"), mapping[7])
        assertEquals(listOf("a","b","c","d","e","f","g"), mapping[8])
        assertEquals(listOf("a","b","c","d","e","f"), mapping[9])
    }
}