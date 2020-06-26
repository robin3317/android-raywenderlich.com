package com.codeonces.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    internal var score: Int = 0
    internal var gameStarted = false

    internal lateinit var tapMeButton: Button
    internal lateinit var scoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView

    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 10000
    internal val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        scoreTextView = findViewById(R.id.gameScoreTextView)
        timeLeftTextView = findViewById(R.id.timeLefttextView)

        tapMeButton.setOnClickListener { view ->
            incrementScore()
        }

        resetGame()
    }

    private fun resetGame() {
        score = 0
        scoreTextView.text = getString(R.string.yourScore, score)

        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.timeLeft, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.timeLeft, timeLeft)

            }

        }

        gameStarted = false
    }

    private fun incrementScore() {
        if(!gameStarted) {
            startGame()
        }

        score++
        val newScore = getString(R.string.yourScore, score)
        scoreTextView.text = newScore
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.gameOverMessage, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}