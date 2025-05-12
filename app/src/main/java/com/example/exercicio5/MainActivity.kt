package com.example.exercicio5

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Taxas de conversão com as moedas fornecidas
    private val taxas = mapOf(
        "Dólar" to 1.1343,           // USD
        "Iene Japonês" to 163.19,    // JPY
        "Libra Esterlina" to 0.8515, // GBP
        "Franco Suiço" to 0.9336,    // CHF
        "Real do Brasil" to 6.4019   // BRL
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar o Spinner com as moedas disponíveis
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.moedas_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMoeda.adapter = adapter

        // Ação do botão Converter
        binding.buttonConverter.setOnClickListener {
            val valorStr = binding.editTextEuros.text.toString()
            val moedaSelecionada = binding.spinnerMoeda.selectedItem.toString()

            if (valorStr.isEmpty()) {
                Toast.makeText(this, "Insira um valor em euros", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val valorEuros = valorStr.toDoubleOrNull()
            if (valorEuros == null) {
                Toast.makeText(this, "Valor incorreto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obter a taxa de conversão para a moeda selecionada
            val taxa = taxas[moedaSelecionada] ?: 1.0
            val valorConvertido = valorEuros * taxa
            val resultado = String.format("%.2f", valorConvertido)

            // Exibir o resultado da conversão
            binding.textViewResultado.text = "Resultado: $resultado $moedaSelecionada"
        }
    }
}
