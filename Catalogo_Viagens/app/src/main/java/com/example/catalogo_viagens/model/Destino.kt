package com.example.catalogo_viagens.model

// Importo a interface Parcelable, necessária para permitir que objetos sejam passados entre activities
import android.os.Parcelable
// Importo o Parcelize, que simplifica a implementação da interface Parcelable
import kotlinx.parcelize.Parcelize

// Anotação que indica ao compilador para gerar automaticamente a implementação do Parcelable
@Parcelize
// Definição da data class 'Destino', que representa um destino de viagem com as suas propriedades
data class Destino(
    val nome: String,         // Nome do destino (ex: "Paris")
    val localizacao: String,  // Localização geográfica ou país (ex: "França")
    val preco: Double,        // Preço estimado da viagem (ex: 1200.0)
    val imagem: Int,          // ID da imagem associada ao destino (recurso drawable)
    val descricao: String     // Descrição detalhada sobre o destino
) : Parcelable // Faço com que esta classe possa ser enviada entre activities (através de intents)
