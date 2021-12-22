package src

import Advent
import java.io.File

class Advent11 : Advent {
    var flashCount : Int = 0
    var flashList : MutableList<MutableList<Int>> = mutableListOf()

    override fun part1() {
        receiveListInput(readInputFromFile())
        runSteps(100)
        println(flashCount)
    }

    override fun part2() {
        TODO("Not yet implemented")
    }

    fun receiveListInput(flash: List<String>) {
        var mutableFlashList : MutableList<MutableList<Int>> = mutableListOf()
        var flashRowList : MutableList<Int> = mutableListOf()
        for(startValues in flash) {
            flashRowList = startValues.split("").filter{e -> e.isNotEmpty()}.map { e -> Integer.valueOf(e) }.toMutableList()
            mutableFlashList.add(flashRowList)
        }
        flashList = mutableFlashList
    }

    fun runSteps(stepCount: Int) {
        for(i in 1..stepCount) {
            updateFlashList(flashList)
        }
    }

    fun updateFlashList(listToUpdate : MutableList<MutableList<Int>>) {
        var mutableListToUpdate = listToUpdate
        for(y in 0..mutableListToUpdate.lastIndex) {
            for(x in 0..mutableListToUpdate[0].lastIndex) {
                mutableListToUpdate[y][x] += 1
            }
        }
        //Check for flashes
        flashList = flashAndUpdateList(mutableListToUpdate)
    }

    fun flashAndUpdateList(listToUpdate: MutableList<MutableList<Int>>) : MutableList<MutableList<Int>> {
        var mutableListToUpdate = listToUpdate
        for(y in 0..mutableListToUpdate.lastIndex) {
            for(x in 0..mutableListToUpdate[0].lastIndex) {
                if(mutableListToUpdate[y][x] > 9) {
                    mutableListToUpdate[y][x] = 0
                    flashCount += 1
                    mutableListToUpdate = updateNearby(y, x, mutableListToUpdate)
                }
            }
        }
        return mutableListToUpdate
    }

    //Given the coordinates of a flashing point, update nearby if not flashed
    fun updateNearby(yPosition: Int, xPosition : Int, listToUpdate: MutableList<MutableList<Int>>) : MutableList<MutableList<Int>> {
        val nearbyCoordinates = listOf(
            Pair(yPosition + 1, xPosition),
            Pair(yPosition + 1, xPosition + 1),
            Pair(yPosition + 1, xPosition - 1),
            Pair(yPosition, xPosition + 1),
            Pair(yPosition, xPosition - 1),
            Pair(yPosition - 1, xPosition),
            Pair(yPosition - 1, xPosition + 1),
            Pair(yPosition - 1, xPosition - 1),
        ).filter { it.first in 0 until listToUpdate.size && it.second in 0 until listToUpdate[0].size }.filterNot { listToUpdate[it.first][it.second] == 0 }
        var mutableListToUpdate = listToUpdate
        for(coords in nearbyCoordinates) {
            val y = coords.first
            val x = coords.second
            mutableListToUpdate[y][x] += 1
        }
        var newMutableList = flashAndUpdateList(mutableListToUpdate)
        if(newMutableList != mutableListToUpdate)
            flashAndUpdateList(mutableListToUpdate)
        return mutableListToUpdate
    }

    fun readInputFromFile() : List<String> = File("data/day11_1.txt").readLines()
}