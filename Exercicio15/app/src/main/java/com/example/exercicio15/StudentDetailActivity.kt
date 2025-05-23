package com.example.exercicio15

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio15.databinding.ActivityStudentDetailBinding
import com.example.exercicio15.model.Student

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val student = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("student", Student::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("student") as? Student
        }

        binding.textViewName.text = "Nome: ${student?.name ?: "N/A"}"
        binding.textViewAddress.text = "Morada: ${student?.address ?: "N/A"}"
        binding.textViewEmail.text = "Email: ${student?.email ?: "N/A"}"
    }
}
