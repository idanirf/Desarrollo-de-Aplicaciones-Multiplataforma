import java.util.*

class Judias {
    val uuid: String = UUID.randomUUID().toString()
    val tipo: String = getType()

    private fun getType(): String {
        val list = listOf("Blancas", "Pintas", "Verdes")
        return list[Random().nextInt(list.size)]
    }

    override fun toString(): String {
        return "Judias(tipo='$tipo')"
    }


}