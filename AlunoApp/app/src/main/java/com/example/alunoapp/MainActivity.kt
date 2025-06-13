package com.example.alunoapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.alunoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AlunoDBHelper
    private var alunoSelecionado: Aluno? = null
    private var ordemAscendente = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AlunoDBHelper(this)
        atualizarLista()

        binding.btnSalvar.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val email = binding.etEmail.text.toString()

            if (alunoSelecionado == null) {
                db.inserirAluno(Aluno(nome = nome, email = email))
            } else {
                alunoSelecionado!!.nome = nome
                alunoSelecionado!!.email = email
                db.atualizarAluno(alunoSelecionado!!)
                alunoSelecionado = null
            }

            binding.etNome.text.clear()
            binding.etEmail.text.clear()
            atualizarLista(binding.etPesquisa.text.toString())
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val aluno = db.listarAlunos().filter {
                it.nome.contains(binding.etPesquisa.text.toString(), ignoreCase = true)
            }.sortedBy {
                if (ordemAscendente) it.nome else it.nome.reversed()
            }[position]
            binding.etNome.setText(aluno.nome)
            binding.etEmail.setText(aluno.email)
            alunoSelecionado = aluno
        }

        binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            val aluno = db.listarAlunos().filter {
                it.nome.contains(binding.etPesquisa.text.toString(), ignoreCase = true)
            }.sortedBy {
                if (ordemAscendente) it.nome else it.nome.reversed()
            }[position]

            AlertDialog.Builder(this)
                .setTitle("Eliminar aluno")
                .setMessage("Tens a certeza que queres apagar '${aluno.nome}'?")
                .setPositiveButton("Sim") { _, _ ->
                    db.apagarAluno(aluno.id)
                    atualizarLista(binding.etPesquisa.text.toString())
                }
                .setNegativeButton("Não", null)
                .show()
            true
        }

        binding.btnOrdenar.setOnClickListener {
            ordemAscendente = !ordemAscendente
            binding.btnOrdenar.text = if (ordemAscendente) "Ordenar A-Z" else "Ordenar Z-A"
            atualizarLista(binding.etPesquisa.text.toString())
        }

        binding.etPesquisa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                atualizarLista(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnLimpar.setOnClickListener {
            binding.etNome.text.clear()
            binding.etEmail.text.clear()
            alunoSelecionado = null
        }
    }

    private fun atualizarLista(filtro: String = "") {
        val alunos = db.listarAlunos().filter {
            it.nome.contains(filtro, ignoreCase = true)
        }.sortedBy {
            if (ordemAscendente) it.nome else it.nome.reversed()
        }

        val nomes = alunos.map { it.nome }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nomes)
        binding.listView.adapter = adapter
        binding.tvTotal.text = "Total: ${alunos.size}"
    }
}
