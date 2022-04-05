package com.example.myquiz
/*definerer et spm, kalles en modell*/

data class Question(
    val id: Int,
    val question: String,
    /* image er int fordi det representer bilder i mappen vår*/
    val image : Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,

    val correctAnswer: Int
)
