package com.example.exercicio7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.LoginErradoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput: EditText = findViewById(R.id.editUsername)
        val passwordInput: EditText = findViewById(R.id.editPassword)
        val loginButton: Button = findViewById(R.id.btnLogin)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == "user" && password == "pass") {
                startActivity(Intent(this, LoginOkActivity::class.java))
            } else {
                startActivity(Intent(this, LoginErradoActivity::class.java))
            }
        }
    }
}
