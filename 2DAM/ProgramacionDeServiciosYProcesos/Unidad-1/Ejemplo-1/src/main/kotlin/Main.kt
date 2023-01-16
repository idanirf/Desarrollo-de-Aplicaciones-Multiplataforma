import java.io.File

fun main(args: Array<String>) {
    //Concurrente y asíncrono
    //Cuando estás lanzando otro proceso o se produce un error el código de salida no va a ser 0.
    val proces = ProcessBuilder("ls", "-ls", ".").start()
    proces.waitFor() //Me espero

    //Kotlin es nativo por lo tanto puedo poner el comando directamente
    val ls = ProcessBuilder("ls", "-ls", ".").start()
    val lsOut = ls.inputStream.bufferedReader().lineSequence()
        .filter { it.contains(".kts") ||
                it.contains(".kt") ||
                it.contains(".bat") }.
        joinToString("\n")

    println(lsOut)

    //Comando cat
    val fCat = lsOut.lines().first().split(" ").last()
    println(fCat)
    //Ejecutar comando cat, con ProcessBuilder le digo el comando y comoo estamos con ProcessBuilder le puedo pasar
    //ficheroCat





}

