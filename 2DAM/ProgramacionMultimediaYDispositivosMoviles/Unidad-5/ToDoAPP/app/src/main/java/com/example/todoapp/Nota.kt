package com.example.todoapp

data class Nota(
    var id: Long,
    var descripcion: String,
    var isFinished: Boolean = false
)