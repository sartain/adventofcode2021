package src

import Advent
import java.io.File

class Advent4: Advent{
    var rows: List<List<Int>> = emptyList() //Rows on every bingo card
    var cols: List<List<Int>> = emptyList() //Cols on every bingo card
    var winningBlock: Int = -1  //Block that has won
    var minimumMovesToWin = 400000;   //Minimum amount of inputs to win
    var maximumMovesToWin = 0;
    var inputList = emptyList<Int>()    //Input list / stream of values

    override fun part1() {
        readInputIntoBoard()
        findMinimumWinner(inputList)
        printScore(getWinningBoard())
    }

    override fun part2() {
        winningBlock = -1
        //readInputIntoBoard()
        //findMaximumWinner()
        findMax(inputList)
        printScoreMaximum(getWinningBoard())
    }

    fun getWinningBoard() : List<List<Int>>{
        val winningIndex = winningBlock * 5
        var winningBoard : List<List<Int>> = emptyList()
        val mutableWinningBoard = winningBoard.toMutableList()
        for(i in winningIndex..winningIndex + 4) {
            mutableWinningBoard.add(rows[i])
        }
        winningBoard = mutableWinningBoard
        return winningBoard
    }

    fun printScore(winningBoard : List<List<Int>>) {
        var score = 0
        val filteredInputList = inputList.slice(0..minimumMovesToWin-1)
        for(row in winningBoard) {
            for (value in row) {
                if(!filteredInputList.contains(value)) {
                    score += value
                }
            }
        }
        val finalResult = score * filteredInputList[filteredInputList.lastIndex]
        println(finalResult)
    }

    fun printScoreMaximum(winningBoard : List<List<Int>>) {
        var score = 0
        val filteredInputList = inputList.slice(0..maximumMovesToWin-1)
        for(row in winningBoard) {
            for (value in row) {
                if(!filteredInputList.contains(value)) {
                    score += value
                }
            }
        }
        val finalResult = score * filteredInputList[filteredInputList.lastIndex]
        println(finalResult)
    }

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

    //Assumes bingo card is yxy where y = number of values

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
    }

    fun findMinimumWinner(inputList : List<Int>) {
        for(i in 0..rows.lastIndex) {
            checkFastestWinner(rows[i], inputList, i / 5)
            checkFastestWinner(cols[i], inputList, i / 5)
        }
    }

    fun findMax(inputList : List<Int>) {
        var maximum = 0
        var blockWinner = 0
        for(i in 0..rows.lastIndex) {
            checkFastestWinner(rows[i], inputList, i / 5)
            checkFastestWinner(cols[i], inputList, i / 5)
            //At each block reset the minimum moves to win
            //Check if there is a new maximum
            if((i+1) % 5 == 0) {
                if(maximum < this.minimumMovesToWin) {
                    blockWinner = i / 5
                    maximum = this.minimumMovesToWin
                }
                this.minimumMovesToWin = 40000
            }
        }
        winningBlock = blockWinner
        maximumMovesToWin = maximum
    }

    fun checkFastestWinner(listToCheck: List<Int>, inputList: List<Int>, grid: Int) {
        var matches = 0;
        var timeTaken = 0;
        for(i in inputList) {
            timeTaken += 1
            if (i in listToCheck) {
                matches += 1
            }
            if(matches == listToCheck.size) {
                if(timeTaken < this.minimumMovesToWin) {
                    winningBlock = grid
                    this.minimumMovesToWin = timeTaken
                    return
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
        }
        addInput(bingoSequence)
    }

    fun readInputFromFile() : List<String> = File("data/day4_1.txt").readLines()

}