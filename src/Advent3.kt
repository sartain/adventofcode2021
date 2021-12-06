package src

import Advent
import java.io.File

class Advent3(var gamma: String = "", var epsilon: String = "", var oxygen: String = "", var cO2: String = "") : Advent {

    fun calculateGammaEpsilon(binaryList : List<String>) {
        val firstString = binaryList.get(0).length
        for(i in 1..firstString) {
            if(zeroMostPopularValueInPosition(i, binaryList)) {
                gamma += "0"
                epsilon += "1"
            }
            else {
                gamma += "1"
                epsilon += "0"
            }
        }
    }

    fun zeroMostPopularValueInPosition(position: Int, binaryList: List<String>) : Boolean {
        return binaryList.map{e -> e.substring(position - 1, position)}.filter { e -> e == "0" }.count() > binaryList.size / 2
    }

    fun multiplyGammaEpsilon(): Int {
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun multiplyOxygenCO2(): Int {
        return Integer.parseInt(cO2, 2) * Integer.parseInt(oxygen, 2)
    }

    fun readInputFromFile() : List<String> = File("data/day3_1.txt").readLines()

    fun calculateOxygenAndC02(binaryList: List<String>) {
        val firstString = binaryList.get(0).length
        var oxygenList = binaryList
        var carbonList = binaryList
        for(i in 1..firstString) {
            if(oxygenList.size == 1){
                oxygen = oxygenList[0]
            }
            if(carbonList.size == 1){
                cO2 = carbonList[0]
            }
            if(differenceBetweenMiddleAndOneValues(i, oxygenList)) {
                oxygenList = filterList(oxygenList, i, "1")
            }
            else{
                oxygenList = filterList(oxygenList, i, "0")
            }
            if(differenceBetweenMiddleAndOneValues(i, carbonList)) {
                carbonList = filterList(carbonList, i, "0")
            }
            else {
                carbonList = filterList(carbonList, i, "1")
            }
        }
        if(oxygenList.size == 1){
            oxygen = oxygenList[0]
        }
        if(carbonList.size == 1){
            cO2 = carbonList[0]
        }
    }

    fun differenceBetweenMiddleAndOneValues(position: Int, binaryList: List<String>) : Boolean {
        return binaryList.map{e -> e.substring(position - 1, position)}.filter { e -> e == "1" }.count() >= binaryList.size.toDouble() / 2
    }

    fun filterList(listToReduce: List<String>, position: Int, valueToFilter: String) : List<String> {
        return listToReduce.filter { e -> e.substring(position-1, position) == valueToFilter }
    }

    override fun part1() {
        calculateGammaEpsilon(readInputFromFile())
        println(multiplyGammaEpsilon())
    }

    override fun part2() {
        calculateOxygenAndC02(readInputFromFile())
        println(multiplyOxygenCO2())
    }

}