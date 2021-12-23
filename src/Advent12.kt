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
        caveList = listOf()
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
        getCaveOptionsGivenLocation("start").forEach {
            getAllLowercaseCaves().forEach { c ->
                navigateCave(it, listOf(), listOf("start"), true, c)
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
        //Logic written out
        //Can revisit a lowercase value once a turn
        //When visit for first time -> ignore but set canvisittwice to false
        //When visit second time -> If canvisittwice set false we add to visited
        //When visit third time -> if in smallcavesvisited we return
        //If can't visit twice (value to revisit != moveTo) and value to revisit lowercase
        //We add to small caves list
        val mutableCavesVisited = cavesVisited.toMutableList()
        val mutableSmallCavesVisited = smallCavesVisited.toMutableList()
        var canReVisit = canVisitTwice
        val viewingCaveToVisitTwiceFirstTime = valueToRevisit == moveTo && canReVisit
        val viewingCaveToVisitTwiceSecondTime = valueToRevisit == moveTo && !canReVisit
        if(mutableSmallCavesVisited.contains(moveTo)) {
            return
        }
        else if(viewingCaveToVisitTwiceFirstTime) {
            canReVisit = false
        }
        else if(viewingCaveToVisitTwiceSecondTime) {
            mutableSmallCavesVisited.add(moveTo)
        }
        else if(moveTo.lowercase() == moveTo) {
            mutableSmallCavesVisited.add(moveTo)
        }
        mutableCavesVisited.add(moveTo)
        if(moveTo == "end") {
            val mutableNavigationList = navigationList.toMutableList()
            if(mutableCavesVisited !in mutableNavigationList)
                mutableNavigationList.add(mutableCavesVisited)
            navigationList = mutableNavigationList
            return
        }
        getCaveOptionsGivenLocation(moveTo).filter { e -> !mutableSmallCavesVisited.contains(e) }.forEach({navigateCave(it, mutableCavesVisited, mutableSmallCavesVisited, canReVisit, valueToRevisit)})
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