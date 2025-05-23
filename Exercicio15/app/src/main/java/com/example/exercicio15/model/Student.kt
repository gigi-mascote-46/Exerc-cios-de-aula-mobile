package com.example.exercicio15.model

import java.io.Serializable

data class Student(
    val name: String,
    val address: String,
    val email: String
) : Serializable
