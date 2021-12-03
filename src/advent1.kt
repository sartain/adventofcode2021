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
        val depthSequence2 = listToSearch.stream().skip(1).mapToInt { e -> Integer.valueOf(e) }.toList()
        val depthSequence3 = listToSearch.stream().skip(2).mapToInt { e -> Integer.valueOf(e) }.toList()
        var count = 0
        var prev = 0
        for(i in 0..depthSequence3.lastIndex) {
            val current =  depthSequence1[i] + depthSequence2[i] + depthSequence3[i]
            if(current > prev && prev != 0) {
                count += 1
            }
            prev = current
        }
        return count
    }

    fun readInputFromFile() : List<String> = File("data/day1_1.txt").readLines()
}

