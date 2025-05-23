// Define o pacote da aplicação
package com.example.exercicio13
// Importações necessárias para usar Views, RecyclerView, Toast, etc.
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Define a classe PessoaAdapter, que herda de RecyclerView.Adapter.
// O adapter recebe um Context (para mostrar o Toast) e uma lista de nomes (List<String>)
class PessoaAdapter(private val context: Context, private val nomes: List<String>) :
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {
    // Classe interna (inner) que representa o ViewHolder, que contém os elementos visuais de cada item
    inner class PessoaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Encontra o TextView no layout do item
        val textNome: TextView = view.findViewById(R.id.textNome)

        // Bloco de inicialização: define o comportamento ao clicar no item
        init {
            view.setOnClickListener {
                // Usa o adapterPosition para obter o nome correspondente ao item clicado
                val nome = nomes[adapterPosition]
                Toast.makeText(context, "Olá $nome", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Cria o ViewHolder: infla o layout de cada item da lista (item_pessoa.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        // Cria a View a partir do XML do item (item_pessoa)
        val view = LayoutInflater.from(context).inflate(R.layout.item_pessoa, parent, false)
        // Retorna uma instância de PessoaViewHolder com essa view
        return PessoaViewHolder(view)

    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.textNome.text = nomes[position]
    }

    override fun getItemCount(): Int = nomes.size
}
