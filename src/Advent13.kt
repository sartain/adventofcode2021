package src

import Advent
import java.io.File
import kotlin.math.max

class Advent13 : Advent {

    var wrapArray : List<List<String>> = listOf()
    var instructionList : List<String> = listOf()

    fun readInputFromFile() : List<String> = File("data/day13_1.txt").readLines()

    override fun part1() {
        receiveInput(readInputFromFile())
        followInstructionsUpTo(1)
        println(countVisibleDotValues())
    }

    override fun part2() {
        wrapArray = listOf()
        instructionList = listOf()
        receiveInput(readInputFromFile())
        followInstructionsUpTo(instructionList.lastIndex)
        printAndDisplay()
    }

    fun receiveInput(inputList : List<String>) {
        var maxX = 0
        var maxY = 0
        for(instruction in inputList) {
            if(instruction.startsWith("fold")) {
                val mutableInstructionList = instructionList.toMutableList()
                mutableInstructionList.add(instruction)
                instructionList = mutableInstructionList
            }
            else {
                val xCoord: Int = Integer.valueOf(instruction.split(",")[0])
                val yCoord: Int = Integer.valueOf(instruction.split(",")[1])
                if (xCoord > maxX)
                    maxX = xCoord
                if (yCoord > maxY)
                    maxY = yCoord
            }
        }
        //create array of size max x max y filling all values with dot
        var mutableWrapArray = wrapArray.toMutableList().toMutableList()
        for(y in 0..maxY) {
            var mutableRow = mutableListOf<String>()
            for(x in 0..maxX) {
                mutableRow.add(".")
            }
            mutableWrapArray.add(mutableRow)
        }
        wrapArray = mutableWrapArray
        for(instruction in inputList) {
            if(instruction.startsWith("fold")) {
                break
            }
            val xCoord : Int = Integer.valueOf(instruction.split(",")[0])
            val yCoord : Int = Integer.valueOf(instruction.split(",")[1])
            var mutableRow = mutableWrapArray[yCoord].toMutableList()
            mutableRow[xCoord] = "#"
            mutableWrapArray[yCoord] = mutableRow
        }
        wrapArray = mutableWrapArray
    }

    fun followInstructionsUpTo(instructionMax : Int) {
        for(i in 0..instructionMax) {
            followInstruction(instructionList[i])
        }
    }

    private fun followInstruction(instruction : String) {
        val axisToFold : String = instruction.split("fold along ")[1].split("=")[0]
        val positionToFold : Int = Integer.valueOf(instruction.split("=")[1])
        if(axisToFold == "x")
            performXFold(positionToFold)
        else
            performYFold(positionToFold)
    }

    fun performXFold(valueToFoldOn : Int) {
        //We want to split the array in half with values before and after the fold
        var mutableWrapArray = wrapArray.toMutableList()
        for(y in 0..wrapArray.lastIndex) {
            for(x in 0..valueToFoldOn) {
                if (wrapArray[y][x] == "#" || wrapArray[y][(valueToFoldOn * 2) - x] == "#") {
                    var mutableRow = mutableWrapArray[y].toMutableList()
                    mutableRow[x] = "#"
                    mutableWrapArray[y] = mutableRow
                } else {
                    var mutableRow = mutableWrapArray[y].toMutableList()
                    mutableRow[x] = "."
                    mutableWrapArray[y] = mutableRow
                }
            }
        }
        //Now split the array
        var splitMutableWrapArray = mutableWrapArray
        for(y in 0..wrapArray.lastIndex) {
            splitMutableWrapArray[y] = mutableWrapArray[y].dropLast(valueToFoldOn + 1)
        }
        wrapArray = splitMutableWrapArray
    }

    fun performYFold(valueToFoldOn : Int) {
        //We want to split the array in half with values before and after the fold
        var mutableWrapArray = wrapArray.toMutableList()
        for(y in 0..valueToFoldOn) {
            for(x in 0..wrapArray[0].lastIndex) {
                if (wrapArray[y][x] == "#" || wrapArray[(valueToFoldOn * 2 - y)][x] == "#") {
                    var mutableRow = mutableWrapArray[y].toMutableList()
                    mutableRow[x] = "#"
                    mutableWrapArray[y] = mutableRow
                } else {
                    var mutableRow = mutableWrapArray[y].toMutableList()
                    mutableRow[x] = "."
                    mutableWrapArray[y] = mutableRow
                }
            }
        }
        //Now split the array
        var splitMutableWrapArray = mutableWrapArray.dropLast(valueToFoldOn + 1)
        wrapArray = splitMutableWrapArray
    }

    fun countVisibleDotValues() : Int{
        var dotCount = 0
        for(row in wrapArray) {
            for(icon in row) {
                if(icon == "#") {
                    dotCount += 1
                }
            }
        }
        return dotCount
    }

    fun printAndDisplay() {
        for(row in wrapArray) {
            for(icon in row) {
                print(icon)
            }
            print("\n")
        }
    }
}