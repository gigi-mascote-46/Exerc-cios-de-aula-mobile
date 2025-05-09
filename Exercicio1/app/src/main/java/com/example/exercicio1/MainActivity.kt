package com.example.exercicio1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declara o binding
    private lateinit var binding: ActivityMainBinding

    // Taxa de conversão Euro → Dólar
    private val taxaConversao = 1.1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding e define a view principal
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ação do botão Converter
        binding.buttonConverter.setOnClickListener {
            val valorEurosStr = binding.editTextEuros.text.toString()

            if (valorEurosStr.isNotEmpty()) {
                val valorEuros = valorEurosStr.toDouble()
                val valorDolares = valorEuros * taxaConversao

                // Arredondando o valor para 2 casas decimais
                val valorDolaresFormatado = String.format("%.2f", valorDolares)

                // Exibindo o resultado na TextView
                binding.textViewResultado.text = "Resultado: $valorDolaresFormatado USD"
            }
        }

    }
}
