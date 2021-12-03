package src

import Advent1

fun main() {
    System.out.println("ADVENT 1:")
    val adv1 = Advent1()
    adv1.printCountOfDepthIncrease()
    System.out.println("...")
    adv1.printCountOfThreeSequenceDepthIncrease()
    System.out.println("ADVENT 2:")
    val adv2 = Advent2(0, 0)
    adv2.runCommandsAndPrintHorizontalByDepth()
    System.out.println("...")
    val adv2_2 = Advent2(0, 0)
    adv2_2.runCommandsWithAimAndPrintHorizontalByDepth()
    System.out.println("ADVENT 3:")
    val adv3_1 = Advent3()
    adv3_1.printAndCalculateGammaEpsilon()
    val adv3_2 = Advent3()
    System.out.println("//")
    adv3_2.printAndCalculateOxygenCO2()
}