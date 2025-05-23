package com.example.exercicio14

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextValor: EditText
    private lateinit var buttonAdicionar: Button
    private lateinit var recyclerViewValores: RecyclerView

    private val listaDeValores = mutableListOf<Int>()
    private lateinit var adapter: ValorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextValor = findViewById(R.id.editTextValor)
        buttonAdicionar = findViewById(R.id.buttonAdicionar)
        recyclerViewValores = findViewById(R.id.recyclerViewValores)

        adapter = ValorAdapter(listaDeValores)
        recyclerViewValores.layoutManager = LinearLayoutManager(this)
        recyclerViewValores.adapter = adapter

        buttonAdicionar.setOnClickListener {
            val texto = editTextValor.text.toString()

            if (texto.isNotEmpty()) {
                try {
                    val valor = texto.toInt()
                    listaDeValores.add(valor)
                    adapter.notifyItemInserted(listaDeValores.size - 1)
                    recyclerViewValores.scrollToPosition(listaDeValores.size - 1)
                    editTextValor.text.clear()
                } catch (_: NumberFormatException) {
                    Toast.makeText(this, "Insere um número inteiro válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Campo vazio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
