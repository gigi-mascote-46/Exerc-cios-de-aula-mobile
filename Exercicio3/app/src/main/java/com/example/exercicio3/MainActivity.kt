package com.example.exercicio3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Cria uma variável de binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Define o layout com o binding
        setContentView(binding.root)

        val taxaConversao = 1.8 // F = C * 1.8 + 32

        binding.buttonConverter.setOnClickListener {
            val valorCelsiusStr = binding.editTextCelsius.text.toString()

            if (valorCelsiusStr.isNotEmpty()) {
                val valorCelsius = valorCelsiusStr.toDouble()
                val valorFahrenheit = valorCelsius * taxaConversao + 32

                // Arredonda o valor para 2 casas decimais
                val valorFahrenheitFormatado = String.format("%.2f", valorFahrenheit)

                // Atualiza a TextView com o valor arredondado
                binding.textViewResultado.text = "Resultado: $valorFahrenheitFormatado °F"
            } else {
                Toast.makeText(this, "Por favor, insira um valor em Celsius", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
