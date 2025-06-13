package com.example.tp_mob.utils

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tp_mob.data.AppDatabase
import com.example.tp_mob.data.entities.Curso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

// Esta classe serve para correr código quando a base de dados é criada pela primeira vez
class DatabaseCallback(
    private val context: Context,          // contexto da aplicação
    private val scope: CoroutineScope      // scope onde vou lançar as coroutines (IO)
) : RoomDatabase.Callback() {

    // Este método é chamado automaticamente quando a BD é criada pela primeira vez
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        // Lanço uma coroutine para não bloquear a thread principal
        scope.launch {
            // Acedo ao DAO da base de dados
            val cursoDao = AppDatabase.getDatabase(context).cursoDao()

            // Defino o formato das datas (para fazer o parse das strings para timestamps)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

            // Lista de cursos de exemplo (pré-preenchimento da BD)
            val cursos = listOf(
                Curso(
                    nome = "Data Analyst",
                    local = "Lisboa",
                    dataArranque = sdf.parse("2025-06-16")!!.time,
                    dataFim       = sdf.parse("2025-08-16")!!.time,
                    preco         = 1500.0,
                    duracao       = 200,
                    edicao        = "1ª Edição",
                    imagem        = "curso_software"
                ),
                Curso(
                    nome = "Android Development",
                    local = "Porto",
                    dataArranque = sdf.parse("2025-07-01")!!.time,
                    dataFim       = sdf.parse("2025-09-01")!!.time,
                    preco         = 1800.0,
                    duracao       = 250,
                    edicao        = "2ª Edição",
                    imagem        = "curso_android"
                ),
                Curso(
                    nome = "Web Development",
                    local = "Online",
                    dataArranque = sdf.parse("2025-06-01")!!.time,
                    dataFim       = sdf.parse("2025-08-01")!!.time,
                    preco         = 1200.0,
                    duracao       = 180,
                    edicao        = "3ª Edição",
                    imagem        = "curso_web"
                )
            )

            // Insere os cursos na base de dados um a um
            cursos.forEach { cursoDao.insert(it) }
        }
    }
}
