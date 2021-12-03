package src

import Advent1

fun main() {
    val adv1 = Advent1()
    adv1.printCountOfDepthIncrease()
    System.out.println("...")
    adv1.printCountOfThreeSequenceDepthIncrease()

    val adv2 = Advent2(0, 0)
    adv2.runCommandsAndPrintHorizontalByDepth()
    System.out.println("...")
    val adv2_2 = Advent2(0, 0)
    adv2_2.runCommandsWithAimAndPrintHorizontalByDepth()
}