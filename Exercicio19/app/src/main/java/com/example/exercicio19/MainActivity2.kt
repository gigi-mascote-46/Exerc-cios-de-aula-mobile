package com.example.exercicio19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio19.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usar ViewBinding para acesso fácil às views
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Preencher os dados recebidos do intent
        binding.detailImage.setImageResource(intent.getIntExtra("imageResId", 0))
        binding.detailName.text = intent.getStringExtra("name")
        binding.detailLocation.text = intent.getStringExtra("location")
        binding.detailPrice.text = intent.getStringExtra("price")
        binding.detailDescription.text = intent.getStringExtra("description")

        // Botão para voltar atrás
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
