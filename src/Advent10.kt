package src

class Advent10 {
    var score: Int = 0
    val opening = listOf("{","(","<","[")
    val closing = listOf("}",")",">","]")
    val points = listOf(1197, 3, 25137, 57)

    fun fromInputReportErrors(inputStrings: List<List<String>>) {
        for(line in inputStrings) {
            findErrorsInLine(line)
        }
    }

    fun findErrorsInLine(inputList: List<String>) {
        var stack = ArrayDeque<String>()
        for(symbol in inputList) {
            if(symbol in opening) {
                stack.addFirst(symbol)
            }
            else {
                var poppedValue : String = stack.removeFirst()
                if(closing.indexOf(symbol) != opening.indexOf(poppedValue)) {
                    score += points[closing.indexOf(symbol)]
                    break
                }
            }
        }
    }

}