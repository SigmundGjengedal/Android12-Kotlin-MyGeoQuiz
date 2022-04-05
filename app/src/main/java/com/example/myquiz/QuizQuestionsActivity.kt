package com.example.myquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

//  inheriting AppCompatActivity(), implements View.OnClickListener (makes items in it clickable)
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

 /* fields for views og variabler. Er utenfor OnCreate, ettersom klassen har flere metoder som trenger dem.
    fordi de er utenfor en metode må variabler settes til en standardverdi / views til null.*/

    // variabler
    private var mCurrentPosition : Int = 1
    private var mQuestionsList:ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    // var for å lagre det som kommer med putExtra
    private var mUserName : String? = null

    // views
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestions : TextView? = null
    private var ivImage : ImageView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null

    private var btnSubmit : Button? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar =findViewById(R.id.progressBar)
        tvProgress =findViewById(R.id.tv_progress)
        tvQuestions =findViewById(R.id.tv_questions)
        ivImage =findViewById(R.id.iv_image)
        btnSubmit= findViewById(R.id.btn_submit)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        // setter onclick via this ( this referer til klassen som implementer OnClick...)
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        // ****** henter data
        // spm fra metode i Constants
        mQuestionsList = Constants.getQuestions();
        //data som kommer via intent
        mUserName = intent.getStringExtra(Constants.USER_NAME)


        // kaller hm fra OnCreate
        setQuestion()
        defaultOptionsView()
    }

    // her henter vi fra Constants, og setter xml atr. Brukes som onClick på submitBtn
    private fun setQuestion() {
        // dbugg: logger til LogCat, må søke der.
    /*        Log.i("questionsList size is", "${mQuestionsList!!.size}")
        for (i in mQuestionsList!!) {
            Log.e("Questions", i.question)
        } */

        // resetter fargene
        defaultOptionsView()
        // henter spm fra questionList
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        // setter fields xml fila:
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestions?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        // Submit button. Styler den avhengig av om man er ferdig eller ikke.
        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text ="FINISH"
        }else{
            btnSubmit?.text="SUBMIT"
        }
    }

    // styler svaralternativ. Denne funksjonen setter default.
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        // bruker let, (et lambda expression) fordi vi har med nullables.
        tvOptionOne?.let{
            options.add(0,it)
        }
        tvOptionTwo?.let{
            options.add(1,it)
        }
        tvOptionThree?.let{
            options.add(2,it)
        }
        tvOptionFour?.let{
            options.add(3,it)
        }
        for(opt in options){
            opt.setTextColor(Color.parseColor("#7A8089"))
            opt.typeface = Typeface.DEFAULT
            opt.background= ContextCompat.getDrawable(this,
            R.drawable.default_option_border_bg
                )
        }

    }

    // styler valgte svaralternativ, brukes i onClick metoden
    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#0000FF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    // styler riktig spm. Brukes i onclick på SubmitBtn
    private fun answerView(answer : Int, drawableView: Int ){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }


    // onclick for alle clickables i denne activity
    override fun onClick(view: View?) {
        when(view?.id){
            // is:
            R.id.tv_option_one ->{
                tvOptionOne?.let{
                selectedOptionView(it, 1)
                }
            }

            R.id.tv_option_two ->{
                tvOptionTwo?.let{
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three ->{
                tvOptionThree?.let{
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four ->{
                tvOptionFour?.let{
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_submit ->{
                // blokk kun for første spm
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++
                // sjekker hvor langt vi er kommet
                    when{
                        mCurrentPosition<= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else ->{
                            // sender bruker til ResultActivity
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers )
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList?.size)
                            startActivity(intent)
                            finish() // backbutton fører til startscreen.
                        }
                    }
                } // for alle spm etter 1.
                    else{
                    val question = mQuestionsList?.get(mCurrentPosition-1) // henter spm
                    if(question!!.correctAnswer!= mSelectedOptionPosition){ // feil svar
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{ // rett svar
                        mCorrectAnswers++
                    }
                    // styler det riktige spm (uansett svar)
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text= "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }


}