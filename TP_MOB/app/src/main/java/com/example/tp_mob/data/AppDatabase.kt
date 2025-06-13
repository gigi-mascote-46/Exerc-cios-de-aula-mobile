package com.example.tp_mob.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp_mob.data.dao.CursoDao
import com.example.tp_mob.data.entities.Curso
import com.example.tp_mob.utils.DatabaseCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

// Esta anotação indica que estamos a criar uma base de dados Room
// Neste caso, temos só uma entidade: Curso
@Database(entities = [Curso::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Declaro a função que me dá acesso ao DAO de Curso
    abstract fun cursoDao(): CursoDao

    companion object {
        // INSTANCE é uma instância única da base de dados (singleton)
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Método que me dá a instância da base de dados (ou cria, se ainda não existir)
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Cria uma CoroutineScope para correr o callback de forma assíncrona no Dispatcher IO
                val callbackScope = CoroutineScope(Dispatchers.IO)

                // Criação da base de dados com Room
                val instance = Room.databaseBuilder(
                    context.applicationContext,     // uso o contexto da aplicação para evitar leaks
                    AppDatabase::class.java,        // classe da base de dados
                    "cesae_database"                // nome do ficheiro da base de dados
                )
                    // Adiciono o callback personalizado (por exemplo, para pré-preencher dados)
                    .addCallback(DatabaseCallback(context.applicationContext, callbackScope))
                    .build()

                // Guardo a instância para reutilizar
                INSTANCE = instance
                instance
            }
        }
    }
}
