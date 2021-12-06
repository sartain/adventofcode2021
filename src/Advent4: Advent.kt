package src

import Advent

class Advent4 () : Advent{
    var rows: List<List<Int>> = emptyList()
    var cols: List<List<Int>> = emptyList()
    var winner: Int = -1
    var minimum = 400000;

    override fun part1() {
        TODO("Not yet implemented")
    }
    override fun part2() {
        TODO("Not yet implemented")
    }

    //We have a list of 5 rows
    //We have a list of 5 cols
    //iterate each row to see if it wins (record count)
    //iterate each col to see if it wins (record count)
    //Get minimum winner based on row and col
    //From there iterate list and get the none-used values

    fun addBoard(board: List<List<Int>>) {
        addRows(board)
        addCols(board)
    }

    fun addRows(board : List<List<Int>>) {
        var mutableListOfRows = rows.toMutableList()
        for(row in board) {
            mutableListOfRows.add(row)
        }
        rows = mutableListOfRows
    }

    fun addCols(board : List<List<Int>>) {
        val mutableListOfCols = cols.toMutableList()
        var mutableCol = mutableListOf<Int>()
        for(i in 0..board.lastIndex) {
            for(j in 0..board[0].lastIndex) {
                mutableCol.add(board[j][i])
            }
            mutableListOfCols.add(mutableCol)
            mutableCol = mutableListOf<Int>()
        }
        cols = mutableListOfCols
    }

    fun addInput(inputList: List<Int>) {
        checkRows(inputList)
    }

    fun checkRows(inputList : List<Int>) {
        for(i in 0..rows.lastIndex) {
            checkRow(rows[i], inputList, i / 5)
        }
        for(i in 0..cols.lastIndex) {
            checkCol(cols[i], inputList, i / 5)
        }
    }

    fun checkRow(row : List<Int>, inputList: List<Int>, grid : Int) {
        var matches = 0;
        var timeTaken = 0;
        for(i in inputList) {
            timeTaken += 1
            if (i in row) {
                matches += 1
            }
            if(matches == 3) {
                if(timeTaken < minimum) {
                    winner = grid
                }
            }
        }
    }

    fun checkCol(col : List<Int>, inputList: List<Int>, grid : Int) {
        var matches = 0;
        var timeTaken = 0;
        for(i in inputList) {
            timeTaken += 1
            if (i in col) {
                matches += 1
            }
            if(matches == 3) {
                if(timeTaken < minimum) {
                    winner = grid
                }
            }
        }
    }

}