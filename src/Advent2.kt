package src

import java.io.File

class Advent2(var xPos: Int, var yPos: Int) {

    fun processCommands(commandList: List<String>) {
        for(command in commandList) {
            if(command.startsWith("forward"))
                this.xPos += 2
            else if(command.startsWith("up"))
                this.yPos += 2
            else
                this.yPos -= 2
        }
    }

    fun readInputFromFile() : List<String> = File("data/day2_1.txt").readLines()

}