package src

import Advent
import java.io.File

class Advent13 : Advent {

    var wrapArray : List<List<String>> = listOf()
    var instructionList : List<String> = listOf()

    fun readInputFromFile() : List<String> = File("data/day13_1.txt").readLines()

    override fun part1() {
        convertInputToGridAndInstructions(readInputFromFile())
        followInstructionsUpTo(1)
        println(countVisibleDotValues())
    }

    override fun part2() {
        wrapArray = listOf()
        instructionList = listOf()
        convertInputToGridAndInstructions(readInputFromFile())
        followInstructionsUpTo(instructionList.lastIndex)
        printAndDisplay()
    }

    fun addInstructionToList(instruction : String) {
        val mutableInstructionList = instructionList.toMutableList()
        mutableInstructionList.add(instruction)
        instructionList = mutableInstructionList
    }

    fun navigateInputList(inputList: List<String>) : List<Int> {
        var maxX = 0
        var maxY = 0
        for(instruction in inputList) {
            if(instruction.startsWith("fold")) {
                addInstructionToList(instruction)
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
        return listOf(maxX, maxY)
    }

    fun initializeArrayWithDots(maxX : Int, maxY: Int) {
        val mutableWrapArray = wrapArray.toMutableList().toMutableList()
        for(y in 0..maxY) {
            val mutableRow = mutableListOf<String>()
            for(x in 0..maxX) {
                mutableRow.add(".")
            }
            mutableWrapArray.add(mutableRow)
        }
        wrapArray = mutableWrapArray
    }

    fun setValuesInInstructionListToHash(inputList: List<String>) {
        var mutableWrapArray = wrapArray.toMutableList()
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

    fun convertInputToGridAndInstructions(inputList : List<String>) {
        val maxBoundaries = navigateInputList(inputList)
        val maxX = maxBoundaries[0]
        val maxY = maxBoundaries[1]
        //create array of size max x max y filling all values with dot
        initializeArrayWithDots(maxX, maxY)
        setValuesInInstructionListToHash(inputList)
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

    fun updateValueAfterFold(yPos: Int, xPos: Int, mutableWrapArray: MutableList<List<String>>, valueToSet: String) : MutableList<List<String>> {
        var mutableRow = mutableWrapArray[yPos].toMutableList()
        mutableRow[xPos] = valueToSet
        mutableWrapArray[yPos] = mutableRow
        return mutableWrapArray
    }

    fun performXFold(valueToFoldOn : Int) {
        //We want to split the array in half with values before and after the fold
        var mutableWrapArray = wrapArray.toMutableList()
        for(y in 0..wrapArray.lastIndex) {
            for(x in 0..valueToFoldOn) {
                if (wrapArray[y][x] == "#" || wrapArray[y][(valueToFoldOn * 2) - x] == "#") {
                    mutableWrapArray = updateValueAfterFold(y, x, mutableWrapArray, "#")
                } else {
                    mutableWrapArray = updateValueAfterFold(y, x, mutableWrapArray, ".")
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
                    mutableWrapArray = updateValueAfterFold(y, x, mutableWrapArray, "#")
                } else {
                    mutableWrapArray = updateValueAfterFold(y, x, mutableWrapArray, ".")
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