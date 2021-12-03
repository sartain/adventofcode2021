package src

import java.io.File

class Advent2(var xPos: Int, var yPos: Int) {

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

    fun readInputFromFile() : List<String> = File("data/day2_1.txt").readLines()

}