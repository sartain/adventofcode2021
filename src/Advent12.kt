package src

import Advent
import java.io.File

class Advent12 : Advent{

    var caveList : List<Pair<String, String>> = listOf()
    var navigationList : List<List<String>> = listOf()

    override fun part1() {
        receiveInput(readInputFromFile())
        navigateCaveStartToEnd()
        println(navigationList.size)
    }

    override fun part2() {
        navigationList = listOf()
        receiveInput(readInputFromFile())
        navigateCaveStartToEndPart2()
        println(navigationList.size)
    }

    fun receiveInput(inputList : List<String>) {
        var mutableCaveList = caveList.toMutableList()
        for(caveLink in inputList) {
            val caveA = caveLink.split("-")[0]
            val caveB = caveLink.split("-")[1]
            mutableCaveList.add(Pair(caveA, caveB))
        }
        caveList = mutableCaveList
    }

    fun navigateCaveStartToEnd() {
        //navigate all possible options from start -> end
        //navigate all possible options from start -> A
        //navigate all possible options from start -> b
        navigateCave("start", listOf(), listOf())
    }

    fun navigateCaveStartToEndPart2() {
        //navigate all possible options from start -> end
        //navigate all possible options from start -> A
        //navigate all possible options from start -> b
        for(option in getCaveOptionsGivenLocation("start")) {
            for(lowerCave in getAllLowercaseCaves()) {
                navigateCave(option, listOf(), listOf(), true, lowerCave)
            }
        }
        //navigateCave("start", listOf(), listOf(), true)
    }

    fun navigateCave(moveTo: String, cavesVisited: List<String>, smallCavesVisited: List<String>) {
        if(moveTo in smallCavesVisited) {
            return
        }
        var mutableCavesVisited = cavesVisited.toMutableList()
        mutableCavesVisited.add(moveTo)
        var mutableSmallCavesVisited = smallCavesVisited.toMutableList()
        if(moveTo.lowercase() == moveTo) {
            mutableSmallCavesVisited.add(moveTo)
        }
        if(moveTo == "end") {
            var mutableNavigationList = navigationList.toMutableList()
            if(mutableCavesVisited !in mutableNavigationList)
                mutableNavigationList.add(mutableCavesVisited)
            navigationList = mutableNavigationList
            return
        }
        for(option in getCaveOptionsGivenLocation(moveTo)) {
            navigateCave(option, mutableCavesVisited, mutableSmallCavesVisited)
        }
    }

    fun navigateCave(moveTo: String, cavesVisited: List<String>, smallCavesVisited: List<String>, canVisitTwice: Boolean, valueToRevisit: String = "") {
        var canReVisitSmall = canVisitTwice && moveTo == valueToRevisit
        var canReVisitNextTurn = canVisitTwice
        if(moveTo in smallCavesVisited) {
            return
        }
        val mutableCavesVisited = cavesVisited.toMutableList()
        mutableCavesVisited.add(moveTo)
        val mutableSmallCavesVisited = smallCavesVisited.toMutableList()
        //Add to small caves visited if this isn't the value we can include twice
        if(moveTo.lowercase() == moveTo) {
            if(canReVisitSmall) {
                canReVisitNextTurn = false
            }
            else {
                mutableSmallCavesVisited.add(moveTo)
            }
        }
        if(moveTo == "end") {
            val mutableNavigationList = navigationList.toMutableList()
            if(mutableCavesVisited !in mutableNavigationList)
                mutableNavigationList.add(mutableCavesVisited)
            navigationList = mutableNavigationList
            return
        }
        for(option in getCaveOptionsGivenLocation(moveTo)) {
            navigateCave(option, mutableCavesVisited, mutableSmallCavesVisited, canReVisitNextTurn, valueToRevisit)
        }
    }

    fun getAllLowercaseCaves() : List<String> {
        var mutableLowerList : MutableList<String> = mutableListOf()
        for(cavePair in caveList) {
            if(cavePair.first !in mutableLowerList && cavePair.first.lowercase() == cavePair.first) {
                mutableLowerList.add(cavePair.first)
            }
            if(cavePair.second !in mutableLowerList && cavePair.second.lowercase() == cavePair.second) {
                mutableLowerList.add(cavePair.second)
            }
        }
        return mutableLowerList.filter { e -> e != "start" && e != "end" }.distinct()
    }

    fun getCaveOptionsGivenLocation(currentCave: String) : List<String> {
        val caveLinks = caveList.filter {  it.first == currentCave || it.second == currentCave }
        if(caveLinks.isEmpty()) {
            return listOf()
        }
        else {
            val linksToStart = mutableListOf<String>()
            for(cave in caveLinks) {
                if(cave.first == currentCave)
                    linksToStart.add(cave.second)
                else
                    linksToStart.add(cave.first)
            }
            return linksToStart
        }
    }

    fun readInputFromFile() : List<String> = File("data/day12_1.txt").readLines()

}