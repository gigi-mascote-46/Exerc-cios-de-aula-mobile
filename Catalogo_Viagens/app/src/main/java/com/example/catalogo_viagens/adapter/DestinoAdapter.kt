package com.example.catalogo_viagens.adapter

// Importações necessárias para lidar com intents, views, RecyclerView e binding
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogo_viagens.MainActivity2
import com.example.catalogo_viagens.databinding.ItemDestinoBinding
import com.example.catalogo_viagens.model.Destino
import com.example.catalogo_viagens.viewholder.DestinoViewHolder

// Adaptador que liga a lista de destinos à RecyclerView
class DestinoAdapter(
    private val destinos: List<Destino> // Lista com os destinos a apresentar
) : RecyclerView.Adapter<DestinoViewHolder>() {

    // Método chamado quando é necessário criar uma nova "célula" (view) da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinoViewHolder {
        // Faço o inflate do layout do item (item_destino.xml) e crio o binding
        val binding = ItemDestinoBinding.inflate(
            LayoutInflater.from(parent.context), // uso o contexto do parent
            parent, // parent = RecyclerView
            false // não anexo o layout diretamente à RecyclerView
        )
        // Retorno o ViewHolder com o binding criado
        return DestinoViewHolder(binding)
    }

    // Método chamado para associar dados ao item (view) na posição específica da lista
    override fun onBindViewHolder(holder: DestinoViewHolder, position: Int) {
        val destino = destinos[position] // Obtenho o destino atual com base na posição

        // Ligo os dados ao ViewHolder (isto preenche os campos com nome, imagem, etc.)
        holder.bind(destino)

        // Defino o comportamento do botão "Info" — ao clicar, abre a MainActivity2 com os dados do destino
        holder.binding.buttonInfo.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity2::class.java).apply {
                putExtra("DESTINO", destino) // Envio o objeto destino através do intent
            }
            // Início da nova activity com o destino selecionado
            holder.itemView.context.startActivity(intent)
        }
    }

    // Retorna o número total de itens na lista (necessário para a RecyclerView saber quantos mostrar)
    override fun getItemCount() = destinos.size
}
