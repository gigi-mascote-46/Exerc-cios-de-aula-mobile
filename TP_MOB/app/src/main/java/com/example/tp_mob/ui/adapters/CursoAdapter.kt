package com.example.tp_mob.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_mob.data.entities.Curso
import com.example.tp_mob.databinding.ItemCursoBinding
import java.text.SimpleDateFormat
import java.util.*

class CursoAdapter(
    private var cursos: List<Curso>,                         // Lista de cursos a mostrar
    private val onEditClick: (Curso) -> Unit,                // Função callback para editar
    private val onDeleteClick: (Curso) -> Unit               // Função callback para apagar
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    // ViewHolder responsável por segurar a view de cada item
    inner class CursoViewHolder(val binding: ItemCursoBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Criação da view do item (inflate do layout XML para objeto Kotlin)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val binding = ItemCursoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CursoViewHolder(binding)
    }

    // Associa os dados de um curso à view correspondente
    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursos[position]                                     // Obtenho o curso atual
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())  // Formato da data

        with(holder.binding) {
            // Atribuo os dados do curso às views do layout
            txtNome.text = curso.nome
            txtLocal.text = curso.local
            txtData.text = dateFormat.format(Date(curso.dataArranque))  // Converto timestamp para data legível

            // Vou buscar o ID da imagem com base no nome guardado no campo `imagem`
            val resourceId = root.context.resources.getIdentifier(
                curso.imagem,              // nome da imagem (ex: "curso_android")
                "drawable",
                root.context.packageName
            )
            imgCurso.setImageResource(resourceId)    // Aplico a imagem ao ImageView

            // Defino ações para os botões de editar e apagar
            btnEdit.setOnClickListener { onEditClick(curso) }
            btnDelete.setOnClickListener { onDeleteClick(curso) }
        }
    }

    // Devolve o número de cursos (para o RecyclerView saber quantos itens tem de desenhar)
    override fun getItemCount() = cursos.size

    // Atualiza a lista de cursos com novos dados e redesenha o RecyclerView
    fun updateList(newList: List<Curso>) {
        cursos = newList
        notifyDataSetChanged() // Redesenha todos os itens (poderia ser otimizado com DiffUtil)
    }
}
