import java.io.File

class Advent1 {

    fun countIncreasingDepthFromList(listToSearch: List<String>) : Int{
        var previousDepth = Integer.valueOf(listToSearch[0])
        var count = 0
        for(value in listToSearch) {
            val depthAsInt = Integer.valueOf(value)
            if (depthAsInt > previousDepth) {
                count += 1
            }
            previousDepth = depthAsInt
        }
        return count
    }

    fun printCountOfDepthIncrease() {
        print(countIncreasingDepthFromList(readInputFromFile()))
    }

    fun readInputFromFile() : List<String> = File("data/day1_1.txt").readLines()
}

