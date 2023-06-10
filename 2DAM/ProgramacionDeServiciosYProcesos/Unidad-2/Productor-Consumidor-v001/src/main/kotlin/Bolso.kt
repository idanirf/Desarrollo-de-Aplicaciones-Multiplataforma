data class Bolso(
    val id: Int,
    var modelo: ModeloBolso
)

enum class ModeloBolso {
    FLAMENCO,
    PUZZLE,
    HAMMOCK,
    GATE,
    TOTE
}