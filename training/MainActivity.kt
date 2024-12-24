package com.example.training

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var exitBTN:Button
    private lateinit var startBTN:Button
    private lateinit var nameET:EditText
    private lateinit var sloganET:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        exitBTN = findViewById(R.id.exitButtonBTN)
        startBTN = findViewById(R.id.startButton)
        nameET = findViewById(R.id.nameET)
        sloganET = findViewById(R.id.sloganET)

        startBTN.setOnClickListener {
            val execiseName = nameET.text.toString()
            val execisslogan = sloganET.text.toString()
            if(execiseName.isNotEmpty() && execisslogan.isNotEmpty()){
            val intent = Intent(this, SecondaActivity::class.java)
            intent.putExtra("EXECISE_NAME", execiseName)
            intent.putExtra("SLOGAN", execisslogan)
            startActivity(intent)
            }else{
               nameET.error = "Введите название упражнения"
               sloganET.error = "Введите девиз"
            }
        }

        exitBTN.setOnClickListener {
            finish()
        }
    }
}