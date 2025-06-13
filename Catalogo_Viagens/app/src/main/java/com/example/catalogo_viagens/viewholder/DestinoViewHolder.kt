package com.example.catalogo_viagens.viewholder

// Importações necessárias para usar RecyclerView e o ViewBinding do item
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogo_viagens.databinding.ItemDestinoBinding
import com.example.catalogo_viagens.model.Destino

// ViewHolder responsável por "segurar" os elementos do layout de cada item da lista de destinos
class DestinoViewHolder(
    val binding: ItemDestinoBinding // Recebo o binding correspondente ao layout item_destino.xml
) : RecyclerView.ViewHolder(binding.root) { // A root é usada como referência principal para o item

    // Função que associa os dados do objeto 'Destino' aos elementos visuais
    fun bind(destino: Destino) {
        binding.textNome.text = destino.nome // Define o nome do destino
        binding.textLocalizacao.text = "Localização: ${destino.localizacao}" // Mostra a localização
        binding.textPreco.text = "€${destino.preco}" // Formata o preço com símbolo de euro
        binding.imageView.setImageResource(destino.imagem) // Mostra a imagem do destino

        // Adiciono uma sombra leve à imagem para dar um toque visual mais apelativo
        binding.imageView.elevation = 4f
    }
}
