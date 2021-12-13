package src

import Advent
import java.io.File

class Advent8 : Advent {
    var inputLines: List<String> = listOf()

    override fun part1() {
        loadInput(readInputFromFile())
        println(detectDigits())
    }

    override fun part2() {
        loadInput(readInputFromFile())
        println(detectDigitsPartTwo())
    }
    fun loadInput(inputString: List<String>) {
        inputLines = inputString
    }

    fun readInputFromFile(): List<String> = File("data/day8_1.txt").readLines()

    //Part one count relates to value being 1/4/7/8
    fun detectDigits(): Int {
        var partOneCount = 0
        for (line in inputLines) {
            val fourDigitString = line.split(" | ")[1]
            val individualDigits = fourDigitString.split(" ")
            val digitsToCheck = listOf(2, 4, 3, 7)
            partOneCount += individualDigits.filter { e -> digitsToCheck.contains(e.length) }.count()
        }
        return partOneCount
    }

    //Part one count relates to value being 1/4/7/8
    fun detectDigitsPartTwo(): Int {
        var partTwoCount = 0
        for (line in inputLines) {
            val inputList = mapConnections(line)
            val fourDigitString = line.split(" | ")[1]
            val individualDigits = fourDigitString.split(" ")
            var stringValue = ""
            for (digit in individualDigits) {
                val correspondingConnection = getListOfDigitConnectionsFromString(digit)
                val digitAsValue = detectDigitFromStringList(inputList, correspondingConnection)
                stringValue += digitAsValue.toString()
            }
            partTwoCount += Integer.valueOf(stringValue)
        }
        return partTwoCount
    }

    fun detectDigitFromStringList(inputList: List<List<String>>, listToCheck: List<String>): Int {
        for (list in inputList) {
            if (list.containsAll(listToCheck) && listToCheck.containsAll(list)) {
                return inputList.indexOf(list)
            }
        }
        return -1
    }

    //Logic to find the map connections

    fun mapConnections(line: String): List<List<String>> {
        val digitMappingList: MutableList<MutableList<String>> = mutableListOf(
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )
        val mappingString = line.split(" | ")[0]
        val mappingDigits = mappingString.split(" ")
        //First loop to get what we need to deduce other values
        for (digit in mappingDigits) {
            when(digit.length) {
                2 -> digitMappingList[1] = getListOfDigitConnectionsFromString(digit)
                4 -> digitMappingList[4] = getListOfDigitConnectionsFromString(digit)
                3 -> digitMappingList[7] = getListOfDigitConnectionsFromString(digit)
                7 -> digitMappingList[8] = getListOfDigitConnectionsFromString(digit)
            }
        }
        //Second loop to use our values to fill the board
        for (digit in mappingDigits) {
            val mappingList = getListOfDigitConnectionsFromString(digit)
            if (digit.length == 6) {
                if (mappingList.containsAll(digitMappingList[4]) && digitMappingList[4].isNotEmpty()) {
                    digitMappingList[9] = mappingList
                } else if (mappingList.containsAll(digitMappingList[1]) && digitMappingList[1].isNotEmpty()) {
                    digitMappingList[0] = mappingList
                } else {
                    digitMappingList[6] = mappingList
                }
            }
            else if (digit.length == 5) {
                if (mappingList.containsAll(digitMappingList[1]) && digitMappingList[1].isNotEmpty()) {
                    digitMappingList[3] = mappingList
                }
                //Check overlap between 4 and 2 / 5 values to decide which one to choose
                else {
                    var fourCount = 0
                    for (fourDigit in digitMappingList[4]) {
                        if (mappingList.contains(fourDigit)) {
                            fourCount += 1
                        }
                    }
                    if (fourCount == 2) {
                        digitMappingList[2] = mappingList
                    } else {
                        digitMappingList[5] = mappingList
                    }
                }
            }
        }
        return digitMappingList
    }

    fun getListOfDigitConnectionsFromString(digit: String) : MutableList<String> {
        return digit.split("").filter{e -> e.isNotEmpty()}.sorted().toMutableList()
    }
}