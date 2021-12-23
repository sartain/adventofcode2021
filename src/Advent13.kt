package src

import Advent
import kotlin.math.max

class Advent13 : Advent {

    var wrapArray : List<List<String>> = listOf()

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
            val xCoord : Int = Integer.valueOf(instruction.split(",")[0])
            val yCoord : Int = Integer.valueOf(instruction.split(",")[1])
            var mutableRow = mutableWrapArray[yCoord].toMutableList()
            mutableRow[xCoord] = "#"
            mutableWrapArray[yCoord] = mutableRow
        }
        wrapArray = mutableWrapArray
    }
}