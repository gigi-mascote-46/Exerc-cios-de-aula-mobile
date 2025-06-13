package com.example.alunoapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AlunoDBHelper(context: Context) : SQLiteOpenHelper(context, "alunos.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE aluno (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS aluno")
        onCreate(db)
    }

    fun inserirAluno(aluno: Aluno) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nome", aluno.nome)
            put("email", aluno.email)
        }
        db.insert("aluno", null, values)
        db.close()
    }

    fun atualizarAluno(aluno: Aluno) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nome", aluno.nome)
            put("email", aluno.email)
        }
        db.update("aluno", values, "id = ?", arrayOf(aluno.id.toString()))
        db.close()
    }

    fun apagarAluno(id: Int) {
        val db = writableDatabase
        db.delete("aluno", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    fun listarAlunos(): List<Aluno> {
        val lista = mutableListOf<Aluno>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM aluno", null)
        if (cursor.moveToFirst()) {
            do {
                val aluno = Aluno(
                    id = cursor.getInt(0),
                    nome = cursor.getString(1),
                    email = cursor.getString(2)
                )
                lista.add(aluno)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }
}
