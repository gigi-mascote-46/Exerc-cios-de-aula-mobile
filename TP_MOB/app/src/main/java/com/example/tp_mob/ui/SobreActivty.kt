package com.example.tp_mob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_mob.databinding.ActivitySobreBinding

// Activity simples para mostrar informação "Sobre"
class SobreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout com View Binding (associado a activity_sobre.xml)
        val binding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define o texto que aparece no ecrã com o nome do/a developer
        binding.txtSobre.text = "Desenvolvido por:\n[Ângela Peixoto]\n"
    }
}
