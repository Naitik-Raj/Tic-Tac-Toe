package com.example.tic_tac_toe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.tic_tac_layout.*

class TicTacLayoutActivity : AppCompatActivity(), View.OnClickListener {

    private var PLAYER = true
    private var TURN_COUNT = 0

    private var boardStatus = Array(3){IntArray(3)}
    private lateinit var board: Array<Array<Button>>

    private lateinit var player1Name: String
    private lateinit var player2Name: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tic_tac_layout)
        player1Name = intent.getStringExtra("PLAYER1").toString()
        player2Name = intent.getStringExtra("PLAYER2").toString()
        tvDisplay.text = "Player X Turn"
        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        btnReset.setOnClickListener {
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
        }
    }



    private fun initializeBoardStatus() {
        for (i in 0..2){
            for (j in 0..2){
                boardStatus[i][j] = -1

            }
        }
        for (i in board){
            for (button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button1->{
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2->{
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3->{
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4->{
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5->{
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6->{
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7->{
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8->{
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9->{
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if (PLAYER){
            updateDisplay("Player $player1Name Turn")
        }else{
            updateDisplay("Player $player2Name Turn")
        }
        if(TURN_COUNT == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }
    private fun checkWinner() {
        //col
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player $player1Name Winner")
                    break
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Player $player2Name Winner")
                    break
                }
            }
        }
        //rows
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player $player1Name Winner")
                    break
                }else if(boardStatus[i][0] == 0){
                    updateDisplay("Player $player2Name Winner")
                    break
                }
            }
        }
        //right diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                updateDisplay("Player $player1Name Winner")
            }else if(boardStatus[0][0] == 0){
                updateDisplay("Player $player2Name Winner")
            }
        }
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player $player1Name Winner")
            }else if(boardStatus[0][2] == 0){
                updateDisplay("Player $player2Name Winner")
            }
        }
    }

    private fun updateDisplay(text: String) {
        tvDisplay.text = text
        if(text.contains("Winner")){
            disableButton()
            if(PLAYER)
            {
                Toast.makeText(this, "Congratulation! $player2Name You Win.", Toast.LENGTH_LONG)
                    .show()
            }else{
                Toast.makeText(this, "Congratulation! $player1Name You Win.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun disableButton() {
        for (i in board){
            for (button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
            val text = if(player) "X" else "O"
            val value = if(player) 1 else 0

            board[row][col].apply {
                isEnabled = false
                setText(text)
            }
            boardStatus[row][col] = value
    }
}