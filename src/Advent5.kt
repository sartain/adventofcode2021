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
        val xBefore = Integer.valueOf(coordinatesBefore.split(",")[0])
        val yBefore = Integer.valueOf(coordinatesBefore.split(",")[1])
        val xAfter = Integer.valueOf(coordinatesAfter.split(",")[0])
        val yAfter = Integer.valueOf(coordinatesAfter.split(",")[1])
        if(xBefore == xAfter) {
            if(yBefore < yAfter) {
                updateGridWhenXValueMatches(yBefore, yAfter, xBefore)
            }
            else if(yBefore > yAfter){
                updateGridWhenXValueMatches(yAfter, yBefore, xBefore)
            }
            else {
                grid["$xBefore:$yBefore"] = grid.getOrDefault("$xBefore:$yBefore", 0) + 1
            }
        }
        else if(yBefore == yAfter) {
            if(xBefore < xAfter) {
                updateGridWhenYValueMatches(xBefore, xAfter, yBefore)
            }
            else if(xBefore > xAfter){
                updateGridWhenYValueMatches(xAfter, xBefore, yBefore)
            }
        }
        if(part2) {
            if(abs(xBefore - xAfter) == abs(yBefore - yAfter)) {
                //Diagonal
                if(xBefore < xAfter) {
                    updateGridBasedOnYDifference(1, yBefore, yAfter, xBefore, xAfter)
                }
                else if(xBefore > xAfter) {
                    updateGridBasedOnYDifference(-1, yBefore, yAfter, xBefore, xAfter)
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

    fun updateGridWhenXValueMatches(lowerValue : Int, upperValue: Int, xValue: Int) {
        for(i in lowerValue..upperValue) {
            grid["$xValue:$i"] = grid.getOrDefault("$xValue:$i", 0) + 1
        }
    }

    fun updateGridWhenYValueMatches(lowerValue : Int, upperValue: Int, yValue: Int) {
        for(i in lowerValue..upperValue) {
            grid["$i:$yValue"] = grid.getOrDefault("$i:$yValue", 0) + 1
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