package src

import Advent
import java.io.File

class Advent6 : Advent {

    var fish : MutableList<Int> = mutableListOf()

    override fun part1() {
        var fishInput : String = readInputFromFile()[0]
        addFishGivenString(fishInput)
        simulateDays(80)
        println(fish.size)
    }

    override fun part2() {
        fish = mutableListOf()
        var fishInput : String = readInputFromFile()[0]
        addFishGivenString(fishInput)
        println(updateFishCountConcisely(256) + fish.size)
    }

    fun addFishGivenString(input : String) {
        val listOfFish = input.split(",").map { e -> Integer.valueOf(e) }
        fish = listOfFish.toMutableList()
    }

    fun simulateDays(dayCount: Int) {
        for(i in 1..dayCount) {
            updateFishCount()
        }
    }

    fun updateFishCountConcisely(days: Int) : Long{
        var mutableFishMap = mutableMapOf<Int, Long>()
        for(i in 0..fish.lastIndex) {
            mutableFishMap[fish[i]] = mutableFishMap.getOrDefault(fish[i], 0) + 1
        }
        return simulateDaysUsingMap(days, mutableFishMap)
    }

    fun simulateDaysUsingMap(days : Int, mutableFishMap : MutableMap<Int, Long>) : Long{
        //Solution taken from online
        //Learning points -> Consider arrays related to a value index when values are going to be a set
        var total : Long = 0
        for (i in 1..days) {
            val placeholder = mutableFishMap.getOrDefault(0, 0)
            mutableFishMap[0] = mutableFishMap.getOrDefault(1, 0)
            mutableFishMap[1] = mutableFishMap.getOrDefault(2, 0)
            mutableFishMap[2] = mutableFishMap.getOrDefault(3, 0)
            mutableFishMap[3] = mutableFishMap.getOrDefault(4, 0)
            mutableFishMap[4] = mutableFishMap.getOrDefault(5, 0)
            mutableFishMap[5] = mutableFishMap.getOrDefault(6, 0)
            mutableFishMap[6] = mutableFishMap.getOrDefault(7, 0)
            mutableFishMap[7] = mutableFishMap.getOrDefault(8, 0)
            mutableFishMap[6] = placeholder + mutableFishMap.getOrDefault(6, 0)
            mutableFishMap[8] = placeholder
            total += placeholder
        }
        return total
    }


    fun updateFishCount() {
        val newFishList = fish
        for(i in 0..fish.lastIndex) {
            fish[i] -= 1
            if(fish[i] < 0) {
                newFishList.add(newFishList.lastIndex+1, 8)
                fish[i] = 6
            }
        }
        fish = newFishList
    }

    //Think about day 2 and a quicker way of performing the calculation
    //Online hints at:
    //list for each fish type
    //replace each time

    fun readInputFromFile() : List<String> = File("data/day6_1.txt").readLines()
}