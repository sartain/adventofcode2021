package src

import Advent

class Advent6 : Advent {

    var fish : MutableList<Int> = mutableListOf()

    override fun part1() {
        TODO("Not yet implemented")
    }

    override fun part2() {
        TODO("Not yet implemented")
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

    fun updateFishCount() {
        val newFishList = fish
        for(i in 0..fish.lastIndex) {
            fish[i] -= 1
            if(fish[i] == 0) {
                newFishList.add(newFishList.lastIndex+1, 8)
            }
        }
        fish = newFishList
    }
}