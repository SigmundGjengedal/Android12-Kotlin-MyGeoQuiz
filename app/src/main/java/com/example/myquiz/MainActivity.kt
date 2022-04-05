package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Views
        val btnStart : Button = findViewById(R.id.btn_start)
        val etName : EditText = findViewById(R.id.et_name)

        // Setter intent på button
        btnStart.setOnClickListener{
            if(etName.text.isEmpty()){
                Toast.makeText(this, "please enter your name", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                // sender med data :
                intent.putExtra(Constants.USER_NAME,etName.text.toString())
                startActivity(intent) /* må starte den*/
                finish()/*appen avslutter om du går tilbake på neste activity*/
            }
        }

    }
}