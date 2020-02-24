package com.bhd.countriesflagslearn

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import java.util.*


class TestActivity : AppCompatActivity() {
    val listCountriesFileName = "ListCountries.txt"
    val listCountries = ListCountries()
    val listQuestions: MutableList<Question> = arrayListOf()
    var posOfCurQuestion: Int = 0
    var posOfRightAnswer: Int = 0
    var point: Int = 0
    var maxQuestion = 100
    var time = 120

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        listCountries.getCountriesLocal(listCountriesFileName)

        getQuestion()

        showQuetion()

        val timer = findViewById<TextView>(R.id.timerText)
        object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minute = (millisUntilFinished / 1000 / 60)
                val second = (millisUntilFinished / 1000 - minute * 60)
                timer.text = "0" + (millisUntilFinished / 1000 / 60).toString() + ":"

                if (second < 10){
                    timer.text = timer.text.toString() + "0" + second.toString()
                } else {
                    timer.text = timer.text.toString() + second.toString()
                }
            }

            override fun onFinish() {
                timer.setText("done!")

                finishGame()
            }
        }.start()
    }

    fun isExit(name: String): Boolean{
        listQuestions.forEach(){
            it->
            if (name == it.name){
                return true
            }
        }

        return false
    }

    fun getQuestion(){
        while(listQuestions.size < maxQuestion && listQuestions.size < listCountries.listCountries.size) {
            val pos = Random().nextInt(listCountries.listCountries.size)
            var posWrongAns: Int = Random().nextInt(listCountries.listCountries.size - 1)

            //ensure that right and wrong answers are different
            while (pos == posWrongAns){
                posWrongAns = Random().nextInt(listCountries.listCountries.size - 1)
            }

            if (!isExit(listCountries.listCountries[pos].name)){
                var curQuestion = Question()

                curQuestion.name = listCountries.listCountries[pos].name
                curQuestion.rightAns = listCountries.listCountries[pos].flag
                curQuestion.wrongAns = listCountries.listCountries[posWrongAns].flag

                listQuestions.add(curQuestion)
            }
        }
    }

    fun showQuetion(){
        //check out ot maxQuestion and endGame
        if (posOfCurQuestion >= listCountries.listCountries.size - 1){
            finishGame()
            return
        } else if (posOfCurQuestion == maxQuestion - 1){
            maxQuestion += 5
            getQuestion()
        }

        posOfRightAnswer = Random().nextInt(2)

        val imageView1 = findViewById<ImageView>(R.id.answer1)
        val imageView2 = findViewById<ImageView>(R.id.answer2)

        if (posOfRightAnswer == 0){
            Glide.with(this).load(listQuestions[posOfCurQuestion].rightAns).into(imageView1)
            Glide.with(this).load(listQuestions[posOfCurQuestion].wrongAns).into(imageView2)
        } else if (posOfRightAnswer == 1){
            Glide.with(this).load(listQuestions[posOfCurQuestion].rightAns).into(imageView2)
            Glide.with(this).load(listQuestions[posOfCurQuestion].wrongAns).into(imageView1)
        }

        val countryName = findViewById<TextView>(R.id.nameCountry)
        countryName.text = listQuestions[posOfCurQuestion].name

        //show point and timer
        val userPoint = findViewById<TextView>(R.id.userPoint)
        userPoint.text = "Your point: " + point.toString()
    }

    fun onAnswer1Clicked(view: View){
        if (posOfRightAnswer == 0){
            //right
            point++
        }

        posOfCurQuestion++

        showQuetion()
    }

    fun onAnswer2Clicked(view: View){
        if (posOfRightAnswer == 1){
            //right
            point++
        }

        posOfCurQuestion++

        showQuetion()
    }

    fun finishGame() {
        //get point
        val countryName = findViewById<TextView>(R.id.nameCountry)
        countryName.text = point.toString() + "/" + (posOfCurQuestion + 1).toString()

        val numAnswered = findViewById<TextView>(R.id.userPoint)
        numAnswered.text = "Your point:"

        //make img invisible
        val imageView1 = findViewById<ImageView>(R.id.answer1)
        imageView1.isVisible = false
        val imageView2 = findViewById<ImageView>(R.id.answer2)
        imageView2.isVisible = false
    }

    fun onBackButtonClicked(view: View){
        finish()
    }
}
