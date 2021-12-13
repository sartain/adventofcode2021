package src

import Advent
import java.io.File

class Advent9 : Advent{
    var lowPoints: List<Int> = listOf()
    var grid : List<List<Int>> = listOf()

    override fun part1() {
        receiveInput(readInputFromFile())
        findLowPoints()
        calculateAndPrintLowPoints()
    }

    override fun part2() {
        TODO("Not yet implemented")
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
        println(grid)
        var mutableLowPoints = lowPoints.toMutableList()
        for(yPosition in 0..grid.lastIndex) {
            for(xPosition in 0..grid[yPosition].lastIndex) {
                println(xPosition)
                println(yPosition)
                if(positionIsSmallestInNearby(xPosition, yPosition)) {
                    mutableLowPoints.add(grid[xPosition][yPosition])
                }
            }
        }
        lowPoints = mutableLowPoints
    }

    fun positionIsSmallestInNearby(xValue: Int, yValue: Int) : Boolean {
        return !isLeftSmaller(xValue, yValue) && !isRightSmaller(xValue, yValue) && !isDownSmaller(xValue, yValue) && !isUpSmaller(xValue, yValue)
    }

    fun isLeftSmaller(xValue: Int, yValue: Int) : Boolean {
        return xValue > 0 && grid[xValue-1][yValue] <= grid[xValue][yValue]
    }

    fun isRightSmaller(xValue: Int, yValue: Int) : Boolean {
        return xValue < grid[0].lastIndex && grid[xValue+1][yValue] <= grid[xValue][yValue]
    }

    fun isUpSmaller(xValue: Int, yValue: Int) : Boolean {
        return yValue > 0 && grid[xValue][yValue-1] <= grid[xValue][yValue]
    }

    fun isDownSmaller(xValue: Int, yValue: Int) : Boolean {
        return yValue < grid.lastIndex && grid[xValue][yValue+1] <= grid[xValue][yValue]
    }

    fun readInputFromFile(): List<String> = File("data/day9_1.txt").readLines()
}