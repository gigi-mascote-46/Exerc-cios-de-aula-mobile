package com.example.tp_mob.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tp_mob.data.dao.CursoDao
import com.example.tp_mob.data.entities.Curso
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// ViewModel que vai gerir os dados relacionados com os cursos
class CursoViewModel(private val cursoDao: CursoDao) : ViewModel() {

    // Observa os cursos ordenados por nome (ascendente e descendente)
    val cursosByNameAsc: Flow<List<Curso>>  = cursoDao.getCursosByNameAsc()
    val cursosByNameDesc: Flow<List<Curso>> = cursoDao.getCursosByNameDesc()

    // Observa os cursos ordenados por data de arranque (ascendente e descendente)
    val cursosByDateAsc: Flow<List<Curso>>  = cursoDao.getCursosByDateAsc()
    val cursosByDateDesc: Flow<List<Curso>> = cursoDao.getCursosByDateDesc()

    // Inserir um novo curso na BD (corre numa coroutine no scope da ViewModel)
    fun insertCurso(curso: Curso) = viewModelScope.launch {
        cursoDao.insert(curso)
    }

    // Atualizar um curso existente
    fun updateCurso(curso: Curso) = viewModelScope.launch {
        cursoDao.update(curso)
    }

    // Apagar um curso da BD
    fun deleteCurso(curso: Curso) = viewModelScope.launch {
        cursoDao.delete(curso)
    }

    /**
     * Função auxiliar que devolve os cursos ordenados por nome
     * consoante o valor booleano passado (true = ascendente, false = descendente)
     */
    fun getCursosSortedByName(ascending: Boolean): Flow<List<Curso>> {
        return if (ascending) cursosByNameAsc else cursosByNameDesc
    }

    /**
     * Função auxiliar que devolve os cursos ordenados por data de arranque
     * consoante o valor booleano passado (true = ascendente, false = descendente)
     */
    fun getCursosSortedByDate(ascending: Boolean): Flow<List<Curso>> {
        return if (ascending) cursosByDateAsc else cursosByDateDesc
    }

    /**
     * Busca um único curso pelo seu ID (usado por exemplo em editar)
     */
    suspend fun getCursoById(id: Int): Curso? {
        return cursoDao.getCursoById(id)
    }
}

// Factory necessária para criar a ViewModel com um parâmetro personalizado (CursoDao)
class CursoViewModelFactory(private val cursoDao: CursoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Garante que está a criar a ViewModel correta
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(cursoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // erro se for classe errada
    }
}
