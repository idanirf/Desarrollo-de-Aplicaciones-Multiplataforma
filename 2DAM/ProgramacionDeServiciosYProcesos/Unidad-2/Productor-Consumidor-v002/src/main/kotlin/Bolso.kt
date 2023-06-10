data class Bolso(
    val id: Int,
    val modelo: ModeloBolso
)

enum class ModeloBolso {
    FLAMENCO,
    PUZZLE,
    HAMMOCK,
    GATE,
    TOTE
}


