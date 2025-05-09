package com.example.exercicio4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Cria a variável de binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Define o layout com o binding
        setContentView(binding.root)

        // Ação do botão de saudar
        binding.buttonSaudar.setOnClickListener {
            val primeiroNome = binding.editTextFirstName.text.toString()
            val ultimoNome = binding.editTextLastName.text.toString()

            if (primeiroNome.isEmpty() || ultimoNome.isEmpty()) {
                // Se algum nome não for inserido, mostra a mensagem de erro
                binding.textViewSaudacao.text = "Nome não inserido"
            } else {
                // Se ambos os nomes forem inseridos, mostra a saudação
                binding.textViewSaudacao.text = "Olá $primeiroNome $ultimoNome!"
            }
        }
    }
}
