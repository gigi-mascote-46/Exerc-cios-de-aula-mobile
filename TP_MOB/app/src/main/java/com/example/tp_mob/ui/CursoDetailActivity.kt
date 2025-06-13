// Activity responsável por mostrar os detalhes de um curso e permitir editar/criar
package com.example.tp_mob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tp_mob.data.AppDatabase
import com.example.tp_mob.data.entities.Curso
import com.example.tp_mob.databinding.ActivityCursoDetailBinding
import com.example.tp_mob.ui.viewmodels.CursoViewModel
import com.example.tp_mob.ui.viewmodels.CursoViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CursoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursoDetailBinding
    private lateinit var viewModel: CursoViewModel
    private var cursoId: Int = -1 // ID do curso a editar (se existir)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o binding para aceder aos elementos da UI
        binding = ActivityCursoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obter a base de dados e criar a ViewModel com factory personalizada
        val db = AppDatabase.getDatabase(this)
        viewModel = ViewModelProvider(
            this,
            CursoViewModelFactory(db.cursoDao())
        )[CursoViewModel::class.java]

        // Verifica se foi passado um ID (modo edição)
        cursoId = intent.getIntExtra("CURSO_ID", -1)
        if (cursoId != -1) loadCurso() // Se houver ID, carrega os dados do curso

        // Guardar quando o botão é clicado
        binding.btnSave.setOnClickListener { saveCurso() }
    }

    // Função para carregar os dados do curso a partir da base de dados
    private fun loadCurso() {
        CoroutineScope(Dispatchers.IO).launch {
            val curso = viewModel.getCursoById(cursoId)
            curso?.let {
                // Atualizar a UI com os dados do curso
                runOnUiThread {
                    binding.apply {
                        edtNome.setText(it.nome)
                        edtLocal.setText(it.local)
                        edtPreco.setText(it.preco.toString())
                        edtDuracao.setText(it.duracao.toString())
                        edtEdicao.setText(it.edicao)
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        edtDataInicio.setText(sdf.format(Date(it.dataArranque)))
                        edtDataFim.setText(sdf.format(Date(it.dataFim)))
                    }
                }
            }
        }
    }

    // Função para guardar as alterações feitas (ou criar novo curso)
    private fun saveCurso() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Cria um objeto Curso com os dados preenchidos nos campos
        val novo = Curso(
            id = if (cursoId != -1) cursoId else 0, // usa o ID atual ou 0 se for novo
            nome = binding.edtNome.text.toString(),
            local = binding.edtLocal.text.toString(),
            dataArranque = sdf.parse(binding.edtDataInicio.text.toString())?.time ?: 0,
            dataFim = sdf.parse(binding.edtDataFim.text.toString())?.time ?: 0,
            preco = binding.edtPreco.text.toString().toDoubleOrNull() ?: 0.0,
            duracao = binding.edtDuracao.text.toString().toIntOrNull() ?: 0,
            edicao = binding.edtEdicao.text.toString(),
            imagem = "curso_default" // valor por omissão
        )

        // Atualiza ou insere consoante o modo (edição ou criação)
        if (cursoId != -1)
            viewModel.updateCurso(novo)
        else
            viewModel.insertCurso(novo)

        // Fecha a activity e volta atrás
        finish()
    }
}
