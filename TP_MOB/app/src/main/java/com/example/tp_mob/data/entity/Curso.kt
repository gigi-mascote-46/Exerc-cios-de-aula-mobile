package com.example.tp_mob.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Esta classe representa a tabela "cursos" na base de dados Room
@Entity(tableName = "cursos")
data class Curso(
    // Chave primária auto-gerada (ID do curso)
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Nome do curso (ex: "Android com Kotlin")
    val nome: String,

    // Local onde o curso vai decorrer (ex: "Porto", "Online")
    val local: String,

    // Data de arranque do curso em formato timestamp (milissegundos)
    val dataArranque: Long,

    // Data de fim do curso em formato timestamp (milissegundos)
    val dataFim: Long,

    // Preço total do curso em euros
    val preco: Double,

    // Duração total do curso em horas
    val duracao: Int,

    // Informação sobre a edição do curso (ex: "3ª edição")
    val edicao: String,

    // Nome do ficheiro da imagem associada ao curso (deve corresponder a um recurso drawable, ex: "curso_android")
    val imagem: String
)
