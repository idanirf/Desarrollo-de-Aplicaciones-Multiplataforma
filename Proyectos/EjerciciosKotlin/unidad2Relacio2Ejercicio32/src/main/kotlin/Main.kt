fun main(args: Array<String>) {
    var initialNumber = 1
    val finalNumber = 100
    print("Números primos entre $initialNumber y $finalNumber: ")
    while (initialNumber < finalNumber) {
        if (isPrimo(initialNumber))
            print("$initialNumber ")
        ++initialNumber
    }
}

fun isPrimo(num: Int): Boolean {
    var primo = true
    for (i in 2..num / 2) {
        if (num % i == 0) {
            primo = false
            break
        }
    }
    return primo
}
