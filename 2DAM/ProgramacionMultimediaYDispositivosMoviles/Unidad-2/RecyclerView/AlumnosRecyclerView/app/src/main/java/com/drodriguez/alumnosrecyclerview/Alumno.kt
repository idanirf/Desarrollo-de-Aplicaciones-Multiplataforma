package com.drodriguez.alumnosrecyclerview

class Alumno(
    var nombre: String = mutableListOf("Azahara", "Daniel", "Juan", "Jorge", "Patricia", "Nicol", "Sofía").random(),
    var apellidoUno: String = mutableListOf("García", "González", "Rodríguez", "López", "Martínez", "Sánchez", "Pérez").random(),
    var apellidoDos: String = mutableListOf("García", "González", "Rodríguez", "López", "Martínez", "Sánchez", "Pérez").random(),
    var edad: Int = (18..30).random(),
    var notaMedia: Int = (0..10).random(),
) {
}