package com.example.Exercicio16

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Exercicio16
import com.example.exercicio16.databinding.ActivityMainBinding

..databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PREFS_NAME = "LoginPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding.editUsername.setText(prefs.getString("username", ""))

        binding.buttonLogin.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            if (username == "user" && password == "pass") {
                Toast.makeText(this, "Login válido", Toast.LENGTH_SHORT).show()

                prefs.edit().putString("username", username).apply()
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
