package com.example.exercicio17

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PREFS_NAME = "NotasPrefs"
    private val KEY_NOTAS = "notas"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding.textNotas.text = prefs.getString(KEY_NOTAS, "")

        binding.buttonGravar.setOnClickListener {
            val novaNota = binding.editNota.text.toString()
            val notasGuardadas = prefs.getString(KEY_NOTAS, "")
            val todasNotas = notasGuardadas + "\n" + novaNota
            binding.textNotas.text = todasNotas
            prefs.edit().putString(KEY_NOTAS, todasNotas).apply()
            binding.editNota.text.clear()
        }
    }
}
