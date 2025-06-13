package com.example.tp_mob.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_mob.data.AppDatabase
import com.example.tp_mob.data.entities.Curso
import com.example.tp_mob.databinding.ActivityMainBinding
import com.example.tp_mob.ui.adapters.CursoAdapter
import com.example.tp_mob.ui.viewmodels.CursoViewModel
import com.example.tp_mob.ui.viewmodels.CursoViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CursoAdapter

    // Inicializa a base de dados
    private val db by lazy { AppDatabase.getDatabase(this) }

    // Cria a ViewModel usando a factory
    private val viewModel: CursoViewModel by viewModels {
        CursoViewModelFactory(db.cursoDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga o layout com o binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o RecyclerView com o adaptador
        setupRecyclerView()

        // Observa os dados ordenados por nome ascendente (por omissão)
        observeDefaultSort()

        // Configura o botão flutuante para adicionar novos cursos
        setupFAB()
    }

    private fun setupRecyclerView() {
        // Inicializa o adaptador com lista vazia e ações para editar/apagar
        adapter = CursoAdapter(
            listOf(),
            onEditClick = { curso: Curso ->
                // Ao clicar em editar, inicia a CursoDetailActivity com o ID do curso
                startActivity(
                    Intent(this, CursoDetailActivity::class.java)
                        .putExtra("CURSO_ID", curso.id)
                )
            },
            onDeleteClick = { curso: Curso ->
                // Remove o curso da base de dados
                viewModel.deleteCurso(curso)
            }
        )

        // Define o layout do RecyclerView e associa o adaptador
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeDefaultSort() {
        // Observa os cursos ordenados por nome (ascendente)
        lifecycleScope.launch {
            viewModel.getCursosSortedByName(ascending = true)
                .collect { cursos: List<Curso> ->
                    adapter.updateList(cursos)
                }
        }
    }

    private fun setupFAB() {
        // Ao clicar no botão flutuante, abre a activity de detalhe sem ID (novo curso)
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, CursoDetailActivity::class.java))
        }
    }

    // Infla o menu na toolbar
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(com.example.tp_mob.R.menu.main_menu, menu)
        return true
    }

    // Responde às ações do menu (ordenar ou ver sobre)
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            // Ordena por nome ascendente
            com.example.tp_mob.R.id.menu_order_name_asc -> {
                lifecycleScope.launch {
                    viewModel.getCursosSortedByName(ascending = true)
                        .collect { cursos: List<Curso> ->
                            adapter.updateList(cursos)
                        }
                }
                return true
            }

            // Ordena por nome descendente
            com.example.tp_mob.R.id.menu_order_name_desc -> {
                lifecycleScope.launch {
                    viewModel.getCursosSortedByName(ascending = false)
                        .collect { cursos: List<Curso> ->
                            adapter.updateList(cursos)
                        }
                }
                return true
            }

            // Ordena por data de arranque ascendente
            com.example.tp_mob.R.id.menu_order_date_asc -> {
                lifecycleScope.launch {
                    viewModel.getCursosSortedByDate(ascending = true)
                        .collect { cursos: List<Curso> ->
                            adapter.updateList(cursos)
                        }
                }
                return true
            }

            // Ordena por data de arranque descendente
            com.example.tp_mob.R.id.menu_order_date_desc -> {
                lifecycleScope.launch {
                    viewModel.getCursosSortedByDate(ascending = false)
                        .collect { cursos: List<Curso> ->
                            adapter.updateList(cursos)
                        }
                }
                return true
            }

            // Abre a activity "Sobre"
            com.example.tp_mob.R.id.menu_about -> {
                startActivity(Intent(this, SobreActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
