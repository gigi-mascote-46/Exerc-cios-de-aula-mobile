package com.example.exercicio6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar os elementos de UI
        val usernameEditText: EditText = findViewById(R.id.editTextUsername)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)
        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val resultTextView: TextView = findViewById(R.id.textViewResultado)
        val loginStatusImageView: ImageView = findViewById(R.id.imageViewLoginStatus)

        // Ação do botão Login
        buttonLogin.setOnClickListener {

            // Obter os valores dos campos
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validar login
            if (username == "user" && password == "pass") {
                // Login válido
                resultTextView.text = "Login válido"
                resultTextView.setTextColor(resources.getColor(android.R.color.holo_green_dark))
                loginStatusImageView.setImageResource(R.drawable.ic_check) // Imagem de sucesso
                loginStatusImageView.visibility = View.VISIBLE
            } else {
                // Login inválido
                resultTextView.text = "Login inválido"
                resultTextView.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                loginStatusImageView.setImageResource(R.drawable.ic_error) // Imagem de erro
                loginStatusImageView.visibility = View.VISIBLE
            }

            // Apagar os valores dos campos após a validação
            usernameEditText.text.clear()
            passwordEditText.text.clear()
        }
    }
}
