package src

import Advent
import java.io.File
import kotlin.math.abs
import java.util.Collections.max
import java.util.Collections.min

class Advent7 : Advent{

    var crabs = mutableListOf<Int>()

    override fun part1() {
        var fileInput = readInputFromFile()[0]
        convertInputToCrabList(fileInput)
        println(realignCrabs())
    }

    override fun part2() {
        TODO("Not yet implemented")
    }

    fun convertInputToCrabList(input: String) {
        crabs = input.split(",").map { e -> Integer.valueOf(e) }.toMutableList()
    }

    fun readInputFromFile() : List<String> = File("data/day7_1.txt").readLines()

    fun realignCrabs() : Int{
        var valueToCheck = 0
        var currentFuelCost = 0
        var previousFuelCost = 1
        while(currentFuelCost <= previousFuelCost) {
            if(currentFuelCost == 0) {
                previousFuelCost = calculateFuelCost(valueToCheck) + 1
                currentFuelCost = calculateFuelCost(valueToCheck)
            }
            else {
                previousFuelCost = currentFuelCost
                currentFuelCost = calculateFuelCost(valueToCheck)
            }
            valueToCheck += 1
        }
        return previousFuelCost
    }

    fun calculateFuelCost(valueToCheck: Int) : Int{
        var fuelCost = 0
        for(crab in crabs) {
            fuelCost += abs(crab - valueToCheck)
        }
        return fuelCost
    }
}