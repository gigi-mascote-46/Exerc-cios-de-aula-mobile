package com.example.tp_mob.data.dao

import androidx.room.*
import com.example.tp_mob.data.entities.Curso
import kotlinx.coroutines.flow.Flow

@Dao
interface CursoDao {
    // Insere um novo curso na base de dados
    @Insert
    suspend fun insert(curso: Curso)

    // Atualiza um curso existente na base de dados
    @Update
    suspend fun update(curso: Curso)

    // Remove um curso da base de dados
    @Delete
    suspend fun delete(curso: Curso)

    // Vai buscar todos os cursos ordenados por nome de forma ascendente (A-Z)
    @Query("SELECT * FROM cursos ORDER BY nome ASC")
    fun getCursosByNameAsc(): Flow<List<Curso>>

    // Vai buscar todos os cursos ordenados por nome de forma descendente (Z-A)
    @Query("SELECT * FROM cursos ORDER BY nome DESC")
    fun getCursosByNameDesc(): Flow<List<Curso>>

    // Vai buscar todos os cursos ordenados pela data de arranque, do mais antigo para o mais recente
    @Query("SELECT * FROM cursos ORDER BY dataArranque ASC")
    fun getCursosByDateAsc(): Flow<List<Curso>>

    // Vai buscar todos os cursos ordenados pela data de arranque, do mais recente para o mais antigo
    @Query("SELECT * FROM cursos ORDER BY dataArranque DESC")
    fun getCursosByDateDesc(): Flow<List<Curso>>

    // Vai buscar um curso específico através do seu ID
    @Query("SELECT * FROM cursos WHERE id = :id")
    suspend fun getCursoById(id: Int): Curso?
}
