package com.example.exercicio7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// This is the SecondActivity class that represents the second screen of the application.
// It extends AppCompatActivity to provide compatibility support for older Android versions.
// The class is empty for now, but it can be expanded with functionality as needed.
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
