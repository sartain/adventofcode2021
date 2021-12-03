import java.io.File

fun main() {
    var myData: List<String> = readInputFromFile()
    print(myData[1])
}

fun readInputFromFile() : List<String> = File("data/day1_1.txt").readLines()