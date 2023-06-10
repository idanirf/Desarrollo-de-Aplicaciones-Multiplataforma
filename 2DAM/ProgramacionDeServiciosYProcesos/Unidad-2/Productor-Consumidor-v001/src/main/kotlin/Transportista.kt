class Transportista(private val fabrica: FabricaBolsos, private val iteraciones: Int) : Thread() {
    override fun run() {
        repeat(iteraciones) {
            fabrica.consumirBolso()
            Thread.sleep(2000)
        }
    }
}