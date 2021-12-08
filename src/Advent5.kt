package src

class Advent5 {
    var grid: MutableMap<String,Int> = mutableMapOf()

    fun markSquaresGivenInput(input: String) {
        val slicedInput = input.split(" -> ")
        val coordinatesBefore = slicedInput[0]
        val coordinatesAfter = slicedInput[1]
        var xBefore = coordinatesBefore.split(",")[0]
        var yBefore = coordinatesBefore.split(",")[1]
        var xAfter = coordinatesAfter.split(",")[0]
        var yAfter = coordinatesAfter.split(",")[1]
        if(xBefore == xAfter) {
            var yBeforeInt = Integer.valueOf(yBefore)
            var yAfterInt = Integer.valueOf(yAfter)
            if(yBeforeInt < yAfterInt) {
                updateGridWhenXValueMatches(yBeforeInt, yAfterInt, xBefore)
            }
            else {
                updateGridWhenXValueMatches(yAfterInt, yBeforeInt, xBefore)
            }
        }
        else if(yBefore == yAfter) {
            var xBeforeInt = Integer.valueOf(xBefore)
            var xAfterInt = Integer.valueOf(xAfter)
            if(xBeforeInt < xAfterInt) {
                updateGridWhenXValueMatches(xBeforeInt, xAfterInt, yBefore)
            }
            else {
                updateGridWhenYValueMatches(xAfterInt, xBeforeInt, yBefore)
            }
        }
    }

    fun updateGridWhenXValueMatches(lowerValue : Int, upperValue: Int, xValue: String) {
        for(i in lowerValue..upperValue) {
            val y = i.toString()
            grid[xValue+y] = grid.getOrDefault(xValue+y, 0) + 1
        }
    }

    fun updateGridWhenYValueMatches(lowerValue : Int, upperValue: Int, yValue: String) {
        for(i in lowerValue..upperValue) {
            val x = i.toString()
            grid[x+yValue] = grid.getOrDefault(x+yValue, 0) + 1
        }
    }
}