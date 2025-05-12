package com.example.exercicio1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val taxaConversao = 1.1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConverter.setOnClickListener {
            val valorEurosStr = binding.editTextEuros.text.toString()

            if (valorEurosStr.isNotEmpty()) {
                val valorEuros = valorEurosStr.toDoubleOrNull()

                if (valorEuros != null) {
                    val valorDolares = valorEuros * taxaConversao
                    val valorDolaresFormatado = String.format("%.2f", valorDolares)
                    binding.textViewResultado.text = "Resultado: $valorDolaresFormatado USD"
                } else {
                    Toast.makeText(this, "Insira um número válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, insira o valor em euros", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
