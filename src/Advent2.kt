package src

import java.io.File

class Advent2(var xPos: Int, var yPos: Int, var aim: Int = 0) {

    fun processCommands(commandList: List<String>) {
        for(command in commandList) {
            val moveBy = Integer.valueOf(command.split(" ")[1])
            if(command.startsWith("forward"))
                this.xPos += moveBy
            else if(command.startsWith("up"))
                this.yPos += moveBy
            else
                this.yPos -= moveBy
        }
    }

    fun processCommandsWithAim(commandList: List<String>) {
        for(command in commandList) {
            val commandValue = Integer.valueOf(command.split(" ")[1])
            if(command.startsWith("forward")) {
                this.xPos += commandValue
                this.yPos += commandValue * this.aim
            }
            else if(command.startsWith("up"))
                this.aim -= commandValue
            else
                this.aim += commandValue
        }
    }

    fun runCommandsAndPrintHorizontalByDepth() {
        processCommands(readInputFromFile())
        print(xPos * yPos)
    }

    fun runCommandsWithAimAndPrintHorizontalByDepth() {
        processCommandsWithAim(readInputFromFile())
        val longX = xPos.toLong()
        val longY = yPos.toLong()
        val result: Long = longX * longY
        print("r" + result)
    }

    fun readInputFromFile() : List<String> = File("data/day2_1.txt").readLines()

}