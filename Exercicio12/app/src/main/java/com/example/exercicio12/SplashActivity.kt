package com.example.exercicio12

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val extras = intent.extras

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SummaryActivity::class.java)
            if (extras != null) {
                intent.putExtras(extras)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}
