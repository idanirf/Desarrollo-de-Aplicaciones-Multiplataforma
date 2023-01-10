fun main(args: Array<String>) {
    println(" Crear un menú de salida que salga cuando introduzcamos (s) o (S)")

    var s: Char
    var S: Char

    do {
        println("¿Desea salir? S/N")
        s = readLine()?.toCharArray() as Char
    } while ((s != s) && (s != S))


}