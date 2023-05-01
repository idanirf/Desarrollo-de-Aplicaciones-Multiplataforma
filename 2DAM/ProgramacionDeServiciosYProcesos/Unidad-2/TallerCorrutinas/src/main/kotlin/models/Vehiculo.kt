package models

import kotlin.random.Random

class Vehiculo(
    var tiempoReparacion: Int = Random.nextInt(3, 300),
    var precio: Int = Random.nextInt(3, 300)
) {
}