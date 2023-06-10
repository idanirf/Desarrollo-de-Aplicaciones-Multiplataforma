class Productor(private val fabrica: FabricaBolsos, private val iteraciones: Int) : Thread() {
    override fun run() {
        val modelos = ModeloBolso.values()
        repeat(iteraciones) {
            val modelo = modelos.random()
            fabrica.producirBolso(modelo)
            Thread.sleep(1000)
        }
    }
}