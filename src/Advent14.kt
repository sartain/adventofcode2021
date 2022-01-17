package src

import Advent

class Advent14 : Advent {

    var splitPairs : List<String> = listOf()

    var pairMappings : List<Pair<String, String>> = listOf()

    override fun part1() {
        TODO("Not yet implemented")
    }

    override fun part2() {
        TODO("Not yet implemented")
    }

    fun convertStringIntoSplitPairs(stringToSplit : String) : MutableList<String>{
        val splitPairs = mutableListOf<String>()
        for(i in 0 until stringToSplit.lastIndex) {
            val splitPair = stringToSplit[i] + "" + stringToSplit[i+1]
            splitPairs.add(splitPair)
        }
        return splitPairs
    }

    fun formatPairMappings(inputList: List<String>) : List<Pair<String, String>>{
        val mutableInputList = inputList.toMutableList()
        val mutablePairMappings = pairMappings.toMutableList()
        mutableInputList.removeAt(0)
        for(input in mutableInputList) {
            val splitInput = input.split(" -> ")
            mutablePairMappings.add(Pair(splitInput[0], splitInput[1]))
        }
        return mutablePairMappings
    }

    fun receiveInput(inputList : List<String>) {
        splitPairs = convertStringIntoSplitPairs(inputList[0])
        pairMappings = formatPairMappings(inputList)
    }
}