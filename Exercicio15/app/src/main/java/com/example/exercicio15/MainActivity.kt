package com.example.exercicio15

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.exercicio15.databinding.ActivityMainBinding
import com.example.exercicio15.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentsList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentsList = listOf(
            Student("João Silva", "Rua A, 123", "joao@mail.com"),
            Student("Maria Santos", "Av. B, 456", "maria@mail.com"),
            Student("Pedro Costa", "Rua C, 789", "pedro@mail.com")
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            studentsList.map { it.name }
        )
        binding.listViewStudents.adapter = adapter

        binding.listViewStudents.setOnItemClickListener { _, _, position, _ ->
            val selectedStudent = studentsList[position]
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("student", selectedStudent)
            startActivity(intent)
        }
    }
}
