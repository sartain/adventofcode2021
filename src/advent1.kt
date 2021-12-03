import java.io.File
import kotlin.streams.toList

class Advent1 {

    fun countIncreasingDepthFromList(listToSearch: List<String>) : Int {
        val depthAsInteger = listToSearch.stream().mapToInt { e -> Integer.valueOf(e) }.toList()
        var count = 0
        depthAsInteger.reduce { a, b -> if (b > a) count++; b}
        return count
    }

    fun printCountOfDepthIncrease() {
        print(countIncreasingDepthFromList(readInputFromFile()))
    }

    fun readInputFromFile() : List<String> = File("data/day1_1.txt").readLines()
}

