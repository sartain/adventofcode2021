package src

import Advent
import java.io.File
import kotlin.math.abs

class Advent5 : Advent{
    var grid: MutableMap<String,Int> = mutableMapOf()

    fun markSquaresGivenInput(input: String, part2: Boolean) {
        val slicedInput = input.split(" -> ")
        val coordinatesBefore = slicedInput[0]
        val coordinatesAfter = slicedInput[1]
        val xBefore = coordinatesBefore.split(",")[0]
        val yBefore = coordinatesBefore.split(",")[1]
        val xAfter = coordinatesAfter.split(",")[0]
        val yAfter = coordinatesAfter.split(",")[1]
        if(xBefore == xAfter) {
            val yBeforeInt = Integer.valueOf(yBefore)
            val yAfterInt = Integer.valueOf(yAfter)
            if(yBeforeInt < yAfterInt) {
                updateGridWhenXValueMatches(yBeforeInt, yAfterInt, xBefore)
            }
            else if(yBeforeInt > yAfterInt){
                updateGridWhenXValueMatches(yAfterInt, yBeforeInt, xBefore)
            }
            else {
                grid["$xBefore:$yBefore"] = grid.getOrDefault("$xBefore:$yBefore", 0) + 1
            }
        }
        else if(yBefore == yAfter) {
            val xBeforeInt = Integer.valueOf(xBefore)
            val xAfterInt = Integer.valueOf(xAfter)
            if(xBeforeInt < xAfterInt) {
                updateGridWhenYValueMatches(xBeforeInt, xAfterInt, yBefore)
            }
            else if(xBeforeInt > xAfterInt){
                updateGridWhenYValueMatches(xAfterInt, xBeforeInt, yBefore)
            }
        }
        if(part2) {
            var xBeforeInt = Integer.valueOf(xBefore)
            var xAfterInt = Integer.valueOf(xAfter)
            var yBeforeInt = Integer.valueOf(yBefore)
            var yAfterInt = Integer.valueOf(yAfter)
            if(abs(xBeforeInt - xAfterInt) == abs(yBeforeInt - yAfterInt)) {
                //Diagonal
                if(xBeforeInt < xAfterInt) {
                    updateGridBasedOnYDifference(1, yBeforeInt, yAfterInt, xBeforeInt, xAfterInt)
                }
                else if(xBeforeInt > xAfterInt) {
                    updateGridBasedOnYDifference(-1, yBeforeInt, yAfterInt, xBeforeInt, xAfterInt)
                }
            }
        }
    }

    fun updateGridBasedOnYDifference(addToX : Int, yBeforeInt: Int, yAfterInt: Int, xBeforeInt: Int, xAfterInt: Int) {
        val yGoal = yAfterInt
        var xToUpdate = xBeforeInt
        var yToUpdate = yBeforeInt
        if(yBeforeInt > yAfterInt) {
            while (yToUpdate >= yGoal) {
                grid["$xToUpdate:$yToUpdate"] = grid.getOrDefault("$xToUpdate:$yToUpdate", 0) + 1
                xToUpdate += addToX
                yToUpdate -= 1
            }
        }
        else if(yBeforeInt < yAfterInt) {
            while (yToUpdate <= yGoal) {
                grid["$xToUpdate:$yToUpdate"] = grid.getOrDefault("$xToUpdate:$yToUpdate", 0) + 1
                xToUpdate += addToX
                yToUpdate += 1
            }
        }
    }

    fun updateGridWhenXValueMatches(lowerValue : Int, upperValue: Int, xValue: String) {
        for(i in lowerValue..upperValue) {
            val y = i.toString()
            grid["$xValue:$y"] = grid.getOrDefault("$xValue:$y", 0) + 1
        }
    }

    fun updateGridWhenYValueMatches(lowerValue : Int, upperValue: Int, yValue: String) {
        for(i in lowerValue..upperValue) {
            val x = i.toString()
            grid["$x:$yValue"] = grid.getOrDefault("$x:$yValue", 0) + 1
        }
    }

    fun readInputFromFile() : List<String> = File("data/day5_1.txt").readLines()

    override fun part1() {
        val inputList = readInputFromFile()
        for(line in inputList) {
            markSquaresGivenInput(line, false)
        }
        println(grid.filter { (key, value) -> value >= 2 }.count())
    }
    override fun part2() {
        grid = mutableMapOf()
        val inputList = readInputFromFile()
        for(line in inputList) {
            markSquaresGivenInput(line, true)
        }
        println(grid.filter { (key, value) -> value >= 2 }.count())
    }
}