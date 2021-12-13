package src

import Advent
import java.io.File

class Advent9 : Advent{
    var lowPoints: List<Int> = listOf()
    var lowPointCoordinates: List<String> = listOf()
    var grid : List<List<Int>> = listOf()
    var basin : List<List<String>> = listOf()

    override fun part1() {
        receiveInput(readInputFromFile())
        findLowPoints()
        calculateAndPrintLowPoints()
    }

    override fun part2() {
        grid = listOf()
        lowPoints = listOf()
        receiveInput(readInputFromFile())
        findLowPoints()
        findBiggestBasins()
        calculateAndPrintThreeBiggestBasins()
    }

    fun calculateAndPrintThreeBiggestBasins() {
        var biggestBasins = basin.distinct().sortedBy { e -> e.size }.takeLast(3)
        var calculation = 1
        for(basin in biggestBasins) {
            calculation *= basin.size
        }
        println(calculation)
    }

    fun calculateAndPrintLowPoints(){
        println(lowPoints.map { e -> e + 1 }.sum())
    }

    fun receiveInput(inputString: List<String>) {
        var mutableGrid = grid.toMutableList()
        for(line in inputString) {
            mutableGrid.add(line.split("").filter{e -> e.isNotEmpty()}.map{ e -> Integer.valueOf(e) })
        }
        grid = mutableGrid
    }

    fun findLowPoints() {
        var mutableLowPoints = lowPoints.toMutableList()
        var mutableLowCoords = lowPointCoordinates.toMutableList()
        for(xPosition in 0..grid.lastIndex) {
            for(yPosition in 0..grid[xPosition].lastIndex) {
                if(positionIsSmallestInNearby(xPosition, yPosition)) {
                    mutableLowPoints.add(grid[xPosition][yPosition])
                    mutableLowCoords.add("$xPosition:$yPosition")
                }
            }
        }
        lowPointCoordinates = mutableLowCoords
        lowPoints = mutableLowPoints
    }

    fun positionIsSmallestInNearby(xValue: Int, yValue: Int) : Boolean {
        return !isLeftSmaller(xValue, yValue) && !isRightSmaller(xValue, yValue) && !isDownSmaller(xValue, yValue) && !isUpSmaller(xValue, yValue)
    }

    fun isLeftSmaller(xValue: Int, yValue: Int) : Boolean {
        return xValue > 0 && grid[xValue-1][yValue] <= grid[xValue][yValue]
    }

    fun isRightSmaller(xValue: Int, yValue: Int) : Boolean {
        return xValue < grid.lastIndex && grid[xValue+1][yValue] <= grid[xValue][yValue]
    }

    fun isUpSmaller(xValue: Int, yValue: Int) : Boolean {
        return yValue > 0 && grid[xValue][yValue-1] <= grid[xValue][yValue]
    }

    fun isDownSmaller(xValue: Int, yValue: Int) : Boolean {
        return yValue < grid[0].lastIndex && grid[xValue][yValue+1] <= grid[xValue][yValue]
    }

    fun findBasin(xValue: Int, yValue: Int, currentBasin: List<String>) : List<String>{
        var mutableBasin = currentBasin.toMutableList()
        if(currentBasin.contains("$xValue:$yValue")) {
            return currentBasin
        }
        else {
            mutableBasin.add("$xValue:$yValue")
            if(isLeftInBasin(xValue, yValue)) {
                mutableBasin += findBasin(xValue-1, yValue, mutableBasin)
            }
            if(isRightInBasin(xValue, yValue)) {
                mutableBasin += findBasin(xValue+1, yValue, mutableBasin)
            }
            if(isUpInBasin(xValue, yValue)) {
                mutableBasin += findBasin(xValue, yValue-1, mutableBasin)
            }
            if(isDownInBasin(xValue, yValue)) {
                mutableBasin += findBasin(xValue, yValue+1, mutableBasin)
            }
        }
        return mutableBasin.distinct()
    }

    fun findBiggestBasins() {
        //Given smallest points
        //Move left and right until filled up basin -> reach 9 values or value not in list
        var mutableBasinList = basin.toMutableList()
        for(lowPoint in lowPointCoordinates) {
            val xPosition = Integer.valueOf(lowPoint.split(":")[0])
            val yPosition = Integer.valueOf(lowPoint.split(":")[1])
            var listToReturn : List<String> = listOf()
            mutableBasinList.add(findBasin(xPosition, yPosition, listToReturn))
        }
        basin = mutableBasinList
    }

    fun isLeftInBasin(xValue: Int, yValue: Int) : Boolean {
        return xValue > 0 && grid[xValue-1][yValue] > grid[xValue][yValue] && grid[xValue-1][yValue] != 9
    }

    fun isRightInBasin(xValue: Int, yValue: Int) : Boolean {
        return xValue < grid.lastIndex && grid[xValue+1][yValue] > grid[xValue][yValue] && grid[xValue+1][yValue] != 9
    }

    fun isUpInBasin(xValue: Int, yValue: Int) : Boolean {
        return yValue > 0 && grid[xValue][yValue-1] > grid[xValue][yValue] && grid[xValue][yValue-1] != 9
    }

    fun isDownInBasin(xValue: Int, yValue: Int) : Boolean {
        return yValue < grid[0].lastIndex && grid[xValue][yValue+1] > grid[xValue][yValue] && grid[xValue][yValue+1] != 9
    }

    fun readInputFromFile(): List<String> = File("data/day9_1.txt").readLines()
}