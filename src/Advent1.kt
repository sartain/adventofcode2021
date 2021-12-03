import java.io.File
import kotlin.streams.toList

class Advent1 {

    fun countIncreasingDepthFromList(listToSearch: List<String>) : Int {
        val depthAsInteger = listToSearch.stream().mapToInt { e -> Integer.valueOf(e) }.toList()
        var count = 0
        depthAsInteger.reduce { a, b -> if (b > a) count++; b}  //Assume second value is what 'a' becomes
        return count
    }

    fun printCountOfDepthIncrease() {
        print(countIncreasingDepthFromList(readInputFromFile()))
    }

    fun printCountOfThreeSequenceDepthIncrease() {
        print(countIncreasingSeqDepthFromList(readInputFromFile()))
    }

    fun countIncreasingSeqDepthFromList(listToSearch: List<String>): Int {
        val depthSequence1 = listToSearch.stream().mapToInt { e -> Integer.valueOf(e) }.toList()
        //val depthSequence2 = listToSearch.stream().skip(1).mapToInt { e -> Integer.valueOf(e) }.toList()
        //val depthSequence3 = listToSearch.stream().skip(2).mapToInt { e -> Integer.valueOf(e) }.toList()
        //Windowed function provides this already.
        var count = 0
        depthSequence1.windowed(3, 1).map{it.sum()}.reduce {a, b -> if (b > a) count ++; b}
        return count
    }

    fun readInputFromFile() : List<String> = File("data/day1_1.txt").readLines()
}

