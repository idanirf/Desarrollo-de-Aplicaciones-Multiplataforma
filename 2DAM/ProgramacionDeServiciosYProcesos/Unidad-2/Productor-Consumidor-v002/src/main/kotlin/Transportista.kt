class Transportista(private val fabrica: FabricaBolsos, private val nombreTransportista: NombreTransportista, private val iteraciones: Int) : Thread() {
    override fun run() {
        repeat(iteraciones) {
            fabrica.consumirBolso(nombreTransportista)
            Thread.sleep(2000)
        }
    }
}
enum class NombreTransportista(val nombre: String) {
    DHL("DHL"),
    UPS("UPS"),
    INTERNO("Interno")
}