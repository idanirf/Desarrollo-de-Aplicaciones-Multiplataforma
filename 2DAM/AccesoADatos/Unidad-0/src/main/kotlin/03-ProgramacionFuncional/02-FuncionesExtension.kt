package `03-ProgramacionFuncional`

import kotlin.math.pow
import kotlin.math.roundToInt

/*
 * Permiten extender la funcionaldiad de una clase sin usar la herencia
 * Detecto que es una función de extensión cuando lleva (/f/)
 */

fun Int.isEven() = this % 2 == 0

fun Int.isOdd() =!this.isEven()

fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return (this * factor).roundToInt() / factor
}

fun String.isPalindrome(): Boolean {
    val reversed = this.reversed()
    return this == reversed
}

//TODO añadir ForEach
//TODO añadir Filter
//TODO añadir Find
//TODO añadir Max


fun main(){
    val num = 10
    println(num.isEven())
    println(num.isOdd())
}