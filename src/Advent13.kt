package src

import Advent
import kotlin.math.max

class Advent13 : Advent {

    var wrapArray : List<List<String>> = listOf()
    var instructionList : List<String> = listOf()

    override fun part1() {
        TODO("Not yet implemented")
    }

    override fun part2() {
        TODO("Not yet implemented")
    }

    fun receiveInput(inputList : List<String>) {
        var maxX = 0
        var maxY = 0
        for(instruction in inputList) {
            if(instruction.startsWith("fold")) {
                val mutableInstructionList = instructionList.toMutableList()
                mutableInstructionList.add(instruction)
                instructionList = mutableInstructionList
                break
            }
            val xCoord : Int = Integer.valueOf(instruction.split(",")[0])
            val yCoord : Int = Integer.valueOf(instruction.split(",")[1])
            if(xCoord > maxX)
                maxX = xCoord
            if(yCoord > maxY)
                maxY = yCoord
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
        for(i in 0..instructionMax-1) {
            followInstruction(instructionList[i])
        }
    }

    private fun followInstruction(instruction : String) {
        val axisToFold : String = instruction.split("fold along ")[1].split("=")[0]
        val positionToFold : Int = Integer.valueOf(instruction.split("=")[1])
        if(axisToFold == "x")
            performXFold(positionToFold)
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
}