fun main() {
    val fabricas = listOf(
        FabricaBolsos(NombreFabrica.MADRID),
        FabricaBolsos(NombreFabrica.VALENCIA),
        FabricaBolsos(NombreFabrica.GRANADA),
    )
    val transportistas = listOf(
        Transportista(fabricas[0], NombreTransportista.DHL, 5),
        Transportista(fabricas[1], NombreTransportista.UPS, 5),
        Transportista(fabricas[2], NombreTransportista.INTERNO, 5)
    )
    val productores = fabricas.map { fabrica -> Productor(fabrica, 5) }

    productores.forEach { productor -> productor.start() }
    transportistas.forEach { transportista -> transportista.start() }

    productores.forEach { productor -> productor.join() }
    transportistas.forEach { transportista -> transportista.join() }

    println("Ejecución terminada")
}