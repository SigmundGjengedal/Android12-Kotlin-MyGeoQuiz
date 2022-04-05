package com.example.myquiz

/*
Et objekt som Holder Konstante variabler til appen Kan bruke dette for å hente dem når vi går fra
en activity til en annen.
Har en metode getQuestions som vi kan bruke i QQactivity.*/
object Constants {

    // disse overskrives i QuizQuestionsActivity
    const val USER_NAME : String ="user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val q1 = Question(
            1,"What Country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Norway",
            "Sweden",
            "Argentina",
            "Austria",
            3
        )
        questionList.add(q1)

        val q2 = Question(
            2,"What Country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Norway",
            "Sweden",
            "Argentina",
            "Australia",
            4
        )
        questionList.add(q2)

        val q3 = Question(
            3,"What Country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark",
            "Sweden",
            "Argentina",
            "Australia",
            1
        )
        questionList.add(q3)

        val q4 = Question(
            4,"What Country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Denmark",
            "Fiji",
            "Argentina",
            "Australia",
            2
        )
        questionList.add(q4)

        val q5 = Question(
            5,"What Country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Denmark",
            "Switzerland",
            "Germany",
            "Australia",
            3
        )
        questionList.add(q5)


        return questionList
    }
}