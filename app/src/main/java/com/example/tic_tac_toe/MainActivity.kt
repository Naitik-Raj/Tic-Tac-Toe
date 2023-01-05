package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener{
            val player_1_turn:String = etPlayer1.text.toString()
            val player_2_turn:String = etPlayer2.text.toString()
            val intent = Intent(this,TicTacLayoutActivity::class.java).apply {
                putExtra("PLAYER1",player_1_turn)
                putExtra("PLAYER2",player_2_turn)
            }

            startActivity(intent)
        }
    }
}