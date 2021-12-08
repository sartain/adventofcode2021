package src

import Advent
import Advent1

fun main() {
    System.out.println("ADVENT 1:")
    val adventDays : List<Advent> = listOf(Advent1(), Advent2(), Advent3(), Advent4(), Advent5())
    var day = 0;
    for(advent in adventDays) {
        day += 1;
        println("Start of day " + day)
        println("Part1")
        advent.part1()
        println("Part2")
        advent.part2()
        println("End of day " + day)
    }
}