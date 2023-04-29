import rentrantLock.Grua
import rentrantLock.Mecanico
import rentrantLock.Parking
import rentrantLock.Taller
import synchronized.GruaSyn
import synchronized.MecanicoSyn
import synchronized.ParkingSyn
import synchronized.TallerSyn
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    /*
    Taller con 5 coches, para arreglar un coche se puede tardar entre 200 y 400 ms, tenemos dos puertas de entrada y
    tres de salida, y tres mecánicos. Procesar 60 coches. No puede haber system.exit.
    Resolver con método synchronized y con lock, además en toer momento el programa debe ser equitativo y justo.
    Pista si es equitativo y justo synchronized descartado.
     */
    println("Taller RentranLock")
    var rentranLock = measureTimeMillis {
        tallerRentranLock()
    }
    println("$rentranLock ms")
    println("\n")


    println("Taller de Synchronized")
    var syncronized = measureTimeMillis {
        tallerDeSynchronized()
    }
    println("$syncronized ms")
}

fun tallerDeSynchronized() {
    var cuantoTardanLasGruas = Pair(200L, 400L)
    var cuantoTardanEnRepararLosmecanicos = Pair(200L, 400L)
    var cuantoDescansasLosMecanicos = Pair(200L, 400L)
    var cuantoCuestaELCoche = Pair(300L, 400L)

    println("Poll parking y taller")
    var poll = Executors.newFixedThreadPool(7)
    val parking = ParkingSyn()
    val taller = TallerSyn()

    repeat(200) {
        parking.añadirPlazasParking(Vehiculo(Random.nextLong(cuantoCuestaELCoche.first, cuantoCuestaELCoche.second)))
    }
    println("Vehiculos metidos en el parking" + parking.buffer.size)

    repeat(2) {
        println("Iniciando grua de Synchronized")
        poll.submit {
            GruaSyn(parking,taller,cuantoTardanLasGruas).run()
        }
    }

    var mecanicos = ArrayList<MecanicoSyn> ()
    repeat(3) {
        println("Iniciando Mecanico Synchronized")
        mecanicos.add(MecanicoSyn(taller, cuantoDescansasLosMecanicos,cuantoTardanEnRepararLosmecanicos)) }

    var futuros = poll.invokeAll(mecanicos)
    var resultadoTotal = futuros.map { it.get() }.sum()
    println("Las ganancias obtenidas con los futures son : "+ resultadoTotal)
    if (!poll.isTerminated){
        Thread.sleep(1)
    }
    poll.shutdown()
}



fun tallerRentranLock() {
    var cuantoTardanLasGruas = Pair(200L, 400L)
    var cuantoTardanEnRepararLosmecanicos = Pair(200L, 400L)
    var cuantoDescansasLosMecanicos = Pair(200L, 400L)
    var cuantoCuestaELCoche = Pair(300L, 400L)
    var hilosAcabados = AtomicInteger(0)

    var poll = Executors.newFixedThreadPool(7)
    val parking = Parking()
    val taller = Taller()

    repeat(200) {
        poll.submit {
            parking.introducirParking(Vehiculo(Random.nextLong(cuantoCuestaELCoche.first, cuantoCuestaELCoche.second)))
            hilosAcabados.addAndGet(1)
        }
    }

    repeat(2) {
        poll.submit {
            Grua(parking,taller,cuantoTardanLasGruas).run()
            hilosAcabados.addAndGet(1)
        }
    }
    var mecanicos = ArrayList<Mecanico> ()
    repeat(3) { mecanicos.add(Mecanico(taller, cuantoDescansasLosMecanicos,cuantoTardanEnRepararLosmecanicos))
        hilosAcabados.addAndGet(1)
    }


    var futuros = poll.invokeAll(mecanicos)
    futuros.forEach {it.get()}
    while (!poll.isTerminated){
        Thread.sleep(1)
        println("Esperando a que termine el poll")
        println("Hilos acabados son "+ hilosAcabados.get())
        if (hilosAcabados.get()==205){
            poll.shutdownNow()
        }
    }
    var resultadoTotal = futuros.map { it.get() }.sum()
    println("Las ganancias obtenidas con los futures son : "+ resultadoTotal)
    poll.shutdown()
}
