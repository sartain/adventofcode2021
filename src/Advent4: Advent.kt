package src

import Advent

class Advent4 (var rows: List<List<Int>> = emptyList(), var cols: List<List<Int>> = emptyList(), var winner: Int = -1) : Advent{
    override fun part1() {
        TODO("Not yet implemented")
    }
    override fun part2() {
        TODO("Not yet implemented")
    }

    fun addBoard(board: List<List<Int>>) {
        addRows(board)
        addCols(board)
    }

    fun addRows(board : List<List<Int>>) {
        var mutableListOfRows = mutableListOf<List<Int>>()
        for(row in board) {
            mutableListOfRows.add(row)
        }
        rows = mutableListOfRows
    }

    fun addCols(board : List<List<Int>>) {
        val mutableListOfCols = mutableListOf<List<Int>>()
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
    }

    fun checkRows(board : List<List<Int>>, inputList : List<Int>) {

    }

    fun checkCols(board : List<List<Int>>, inputList : List<Int>) {

    }

}