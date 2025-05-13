package com.example.exercicio7
// This file is part of the Exercicio7 project.


// It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution.
// No part of the Exercicio7 project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

// This is the SplashActivity class that displays a splash screen for 3 seconds before navigating to the MainActivity.
// It extends AppCompatActivity to provide compatibility support for older Android versions.
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Espera 3 segundos e vai para o MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3 segundos
    }
}