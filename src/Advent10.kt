package src

import Advent
import java.io.File

class Advent10 : Advent{
    var score: Long = 0
    val opening = listOf("{","(","<","[")
    val closing = listOf("}",")",">","]")
    val points = listOf(1197, 3, 25137, 57)

    fun fromInputReportErrors(inputStrings: List<List<String>>) {
        for(line in inputStrings) {
            findErrorsInLine(line)
        }
    }

    fun findErrorsInLine(inputList: List<String>) {
        var stack = ArrayDeque<String>()
        for(symbol in inputList) {
            if(symbol in opening) {
                stack.addFirst(symbol)
            }
            else {
                var poppedValue : String = stack.removeFirst()
                if(closing.indexOf(symbol) != opening.indexOf(poppedValue)) {
                    score += points[closing.indexOf(symbol)]
                    break
                }
            }
        }
    }

    fun convertInputForFunction(inputList: List<String>) : List<List<String>> {
        var listToReturn : List<List<String>> = listOf()
        var mutableListToReturn = listToReturn.toMutableList()
        for(line in inputList) {
            mutableListToReturn.add(line.split("").filter { e -> e.isNotEmpty() })
        }
        return mutableListToReturn
    }

    fun readInputFromFile(): List<String> = File("data/day10_1.txt").readLines()

    override fun part1() {
        var input = readInputFromFile()
        fromInputReportErrors(convertInputForFunction(input))
        println(score)
    }

    override fun part2() {
        TODO("Not yet implemented")
    }

}