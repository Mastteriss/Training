package com.example.training

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mytraining.ExerciseDateBase
import pl.droidsonroids.gif.GifImageView

class SecondaActivity : AppCompatActivity() {

    val exercise = ExerciseDateBase.exercise
    private lateinit var exit:Button
    private lateinit var titleTV: TextView
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var completeButtonBTN: Button
    private lateinit var startButtonBTN: Button
    private lateinit var timerTV: TextView
    private lateinit var imageViewIV: ImageView
    private var exerciseIndex = 0
    private lateinit var currentExecise: Exercise
    private lateinit var timer: CountDownTimer
    private lateinit var exerciseName: String
    private lateinit var slogan: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seconda)

        init()
        exerciseName = intent.getStringExtra("EXECISE_NAME") ?: ""
        slogan = intent.getStringExtra("SLOGAN") ?: ""
        titleTV.text = exerciseName
        descriptionTV.text = slogan
        exerciseIndex = exercise.indexOfFirst { it.name.equals(exerciseName, ignoreCase = true) }
        exit.setOnClickListener {
        finish()
            }
        if (exerciseIndex != -1) {

            startWorkout()
        } else {

            exerciseTV.text = "Упражнение не найдено"
            descriptionTV.text = ""
            timerTV.text = ""
            completeButtonBTN.isEnabled = false
            startButtonBTN.isEnabled = true
            startButtonBTN.text = "Начать заново"
        }

        startButtonBTN.setOnClickListener {
            startWorkout()
        }

        completeButtonBTN.setOnClickListener {
            completeExercise()
        }
    }

    private fun init() {
        exit = findViewById(R.id.exit)
        titleTV = findViewById(R.id.titleTV)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        completeButtonBTN = findViewById(R.id.completeButtonBTN)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        timerTV = findViewById(R.id.timerTV)
        imageViewIV = findViewById(R.id.imageViewIV)
    }

    private fun completeExercise() {
        timer.cancel()
        completeButtonBTN.isEnabled = false
        startNextExercise()
    }

    private fun startNextExercise() {
        timer.cancel()
        completeButtonBTN.isEnabled = false
        startNextExercise()
    }

    private fun startWorkout() {

        if (exerciseIndex < exercise.size) {
            currentExecise = exercise[exerciseIndex]
            exerciseTV.text = currentExecise.name
            descriptionTV.text = currentExecise.duscription
            imageViewIV.setImageResource(currentExecise.gifImage)
            timerTV.text = formatTime(currentExecise.durationInSeconds)
            timer = object : CountDownTimer(currentExecise.durationInSeconds * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerTV.text = formatTime((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    timerTV.text = "Упражнение завершено"
                    imageViewIV.visibility = View.VISIBLE
                    completeButtonBTN.isEnabled = true
                    imageViewIV.setImageResource(0)
                }
            }.start()
            exerciseIndex++
        } else {
            exerciseTV.text = "Тренировка завершена"
            descriptionTV.text = ""
            timerTV.text = ""
            completeButtonBTN.isEnabled = false
            startButtonBTN.isEnabled = true
            startButtonBTN.text = "Начать снова"
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}