package src

import Advent
import java.io.File

class Advent4: Advent{
    var rows: List<List<Int>> = emptyList()
    var cols: List<List<Int>> = emptyList()
    var winningBlock: Int = -1
    var minimum = 400000;
    var inputList = emptyList<Int>()

    override fun part1() {
        readInputIntoBoard()
        val winningIndex = winningBlock * 5
        var winningBoard : List<List<Int>> = emptyList()
        val mutableWinningBoard = winningBoard.toMutableList()
        for(i in winningIndex..winningIndex + 4) {
            mutableWinningBoard.add(rows[i])
        }
        winningBoard = mutableWinningBoard
        printScore(winningBoard)
        //From winner find the correct grid
    }
    override fun part2() {
        TODO("Not yet implemented")
    }

    fun printScore(winningBoard : List<List<Int>>) {
        var score = 0
        val filteredInputList = inputList.slice(0..minimum-1)
        for(row in winningBoard) {
            for (value in row) {
                if(!filteredInputList.contains(value)) {
                    score += value
                }
            }
        }
        System.out.println(score)
        System.out.println(filteredInputList[filteredInputList.lastIndex])
        var finalResult = score * filteredInputList[filteredInputList.lastIndex]
        System.out.println(finalResult)
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
        this.inputList = inputList
        checkRowsAndCols(inputList)
    }

    fun checkRowsAndCols(inputList : List<Int>) {
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
            if(matches == row.size) {
                if(timeTaken < this.minimum) {
                    winningBlock = grid
                    this.minimum = timeTaken
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
            if(matches == col.size) {
                if(timeTaken < this.minimum) {
                    winningBlock = grid
                    this.minimum = timeTaken
                }
            }
        }
    }

    fun readInputIntoBoard() {
        var input : List<String> = readInputFromFile()
        val bingoSequence : List<Int> = input[0].split(",").map { i -> Integer.valueOf(i) }
        var boardToAdd : List<List<Int>> = emptyList()
        var mutableBoardToAdd = boardToAdd.toMutableList()
        var count = 0;
        for(i in 1..input.lastIndex) {
            if(input[i].isNotEmpty()) {
                var row = input[i].split(",").map { i -> Integer.valueOf(i) }
                mutableBoardToAdd.add(row)
                count += 1
            }
            else {
                boardToAdd = mutableBoardToAdd
                addBoard(boardToAdd)
                boardToAdd = emptyList()
                mutableBoardToAdd = boardToAdd.toMutableList()
                count = 0
            }
        }
        if(count == 5) {
            boardToAdd = mutableBoardToAdd
            addBoard(boardToAdd)
            boardToAdd = emptyList()
        }
        addInput(bingoSequence)
    }

    fun readInputFromFile() : List<String> = File("data/day4_1.txt").readLines()

}