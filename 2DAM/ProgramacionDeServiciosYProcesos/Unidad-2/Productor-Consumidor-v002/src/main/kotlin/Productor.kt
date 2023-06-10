// METER VARIOS PRODUCTORES
class Productor(private val fabrica: FabricaBolsos, private val producciones: Int) : Thread() {
    override fun run() {
        val modelos = ModeloBolso.values()
        repeat(producciones) {
            val modelo = modelos.random()
            fabrica.producirBolso(modelo)
            Thread.sleep(1000)
        }
    }
}