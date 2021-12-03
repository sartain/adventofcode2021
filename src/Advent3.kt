package src

import java.io.File

class Advent3(var gamma: String = "", var epsilon: String = "") {

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
        calculateGammaEpsilon(readInputFromFile())
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun readInputFromFile() : List<String> = File("data/day3_1.txt").readLines()

}