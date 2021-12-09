package src

import Advent
import java.io.File

class Advent8 : Advent {
    var inputLines : List<String> = listOf()

    override fun part1() {
        loadInput(readInputFromFile())
        println(detectDigits())
    }

    override fun part2() {
        TODO("Not yet implemented")
    }

    fun loadInput(inputString: List<String>) {
        inputLines = inputString
    }

    fun readInputFromFile() : List<String> = File("data/day8_1.txt").readLines()

    //Part one count relates to value being 1/4/7/8
    fun detectDigits() : Int{
        var partOneCount = 0
        for(line in inputLines) {
            var fourDigitString = line.split(" | ")[1]
            var individualDigits = fourDigitString.split(" ")
            for(digit in individualDigits) {
                if(digit.length == 2 || digit.length == 4 || digit.length == 3 || digit.length == 7) {
                    partOneCount += 1
                }
            }
        }
        return partOneCount
    }
}