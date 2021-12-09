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
        println(realignCrabs(true))
    }

    override fun part2() {
        crabs = mutableListOf()
        var fileInput = readInputFromFile()[0]
        convertInputToCrabList(fileInput)
        println(realignCrabs(false))
    }

    fun convertInputToCrabList(input: String) {
        crabs = input.split(",").map { e -> Integer.valueOf(e) }.toMutableList()
    }

    fun readInputFromFile() : List<String> = File("data/day7_1.txt").readLines()

    fun realignCrabs(partOne: Boolean) : Int{
        var valueToCheck = 0
        var currentFuelCost = 0
        var previousFuelCost = 1
        while(currentFuelCost <= previousFuelCost) {
            if(currentFuelCost == 0) {
                previousFuelCost = calculateFuelCost(valueToCheck, partOne) + 1
                currentFuelCost = calculateFuelCost(valueToCheck, partOne)
            }
            else {
                previousFuelCost = currentFuelCost
                currentFuelCost = calculateFuelCost(valueToCheck, partOne)
            }
            valueToCheck += 1
        }
        return previousFuelCost
    }

    fun calculateFuelCost(valueToCheck: Int, partOne: Boolean) : Int {
        var fuelCost = 0
        for(crab in crabs) {
            if(partOne)
                fuelCost += abs(crab - valueToCheck)
            else {
                val distance = abs(crab - valueToCheck)
                for(i in 0..distance) {
                    fuelCost += i
                }
            }
        }
        return fuelCost
    }
}