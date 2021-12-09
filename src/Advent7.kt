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
        val maximum = max(crabs)
        val minimum = min(crabs)
        var fuelCost = 0
        var minimumFuelCost = 0
        var valueForMinimumCost = 0
        for(i in minimum..maximum) {
            for(crab in crabs) {
                fuelCost += abs(crab - i)
            }
            println(fuelCost)
            if(fuelCost < minimumFuelCost || minimumFuelCost == 0) {
                valueForMinimumCost = i
                minimumFuelCost = fuelCost
            }
            fuelCost = 0
        }
        return minimumFuelCost
    }
}