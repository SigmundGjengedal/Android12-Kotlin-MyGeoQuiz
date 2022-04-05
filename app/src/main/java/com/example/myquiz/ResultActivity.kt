package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // har ingen andre metoder så kan sette variabler direkte:
        val tvUserName : TextView = findViewById(R.id.tv_username)
        val tvScore : TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        //data som kommer via intent
        val mUserName = intent.getStringExtra(Constants.USER_NAME)
        tvUserName.text = mUserName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        tvScore.text ="Your Score is $correctAnswers out of $totalQuestions"

        // setter intent i en linje: Går tilbake til MainActivity.
        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}