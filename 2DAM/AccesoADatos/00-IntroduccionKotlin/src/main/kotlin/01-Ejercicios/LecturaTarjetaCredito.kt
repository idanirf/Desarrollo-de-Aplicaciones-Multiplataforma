fun main() {
    var numeroExpresionTarjeta = Regex("^[0-9]{16}$")
    print("Introduce el numero de la tarjeta: ")
    var numeroTarjeta = readLine()!!
    if (numeroExpresionTarjeta.matches(numeroTarjeta)){
        println("El numero de la tarjeta es correcto ✔")
    }else{
        println("El numero de la tarjeta es incorrecto ❌")
    }
}