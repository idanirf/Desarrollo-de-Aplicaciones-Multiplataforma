import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

suspend fun main(args: Array<String>) {
    judiasNa1Actor()
    unoMuchosProductor()
    channelLimited()
    judiasMuchosAUno()
    judiasNMChannel()
    judiasChannel()
}
suspend fun unoMuchosProductor(){
    val scope = CoroutineScope(Dispatchers.IO)
    var job = ArrayList<Job>()
    var andreaMete = 0

    // Creamos el canal
    println("Lanzamos a Andrea")
    val canal  = scope.produce {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ send(Judias()) }
            println("Andrea mete $num Judias")
            andreaMete+=num
        }
        println("Andrea hasta cansaica se va ya a casa, termino su jornada")
    }
    var list1 = ArrayList<Judias>()
    var list2 = ArrayList<Judias>()
    var list3 = ArrayList<Judias>()

    println("Lanzamos al resto")
    job.add(scope.launch {
        var channelVacioOpen = true
        while(channelVacioOpen){
            try {
                list1.add(canal.receive())
                println("Lanzamos client1")
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
    })
    job.add(scope.launch {
        var channelVacioOpen = true
        while(channelVacioOpen){
            try {
                list2.add(canal.receive())
                println("Lanzamos client2")
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
    })
    job.add(scope.launch {
        var channelVacioOpen = true
        while(channelVacioOpen){
            try {
                list3.add(canal.receive())
                println("Lanzamos client3")
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
    })

    // Sincronización los trabajos
    job.forEach {it.join()}
    println("Al final Reponedor, repone: $andreaMete, client1: ${list1.size}, client2: ${list2.size}, client3: ${list3.size}")
    println("Client 1 recibe: ${list1.size}$list1")
    println("Client 2 recibe: ${list2.size}$list2")
    println("Client 3 recibe: ${list3.size}$list3")
}

suspend fun channelLimited(){
    val productores = 4
    val consumidores = 3
    var movimientos = 5
    var sizeChannel = 8
    var channel = Channel<Judias>(sizeChannel, onBufferOverflow = BufferOverflow.DROP_LATEST)
    var jobsProducer = ArrayList<Job>()
    var jobs = ArrayList<Job>()
    var scope = CoroutineScope(Dispatchers.IO)

    println("Lanzamos a los reponedores")
    repeat(productores){
        jobsProducer.add(scope.launch {
            repeat(movimientos){
                delay(Random.nextLong(1, 100))
                var aded = Random.nextInt(5, 10)
                println("Reparto $aded")
                repeat(aded){channel.send(Judias()) }
            }
            println("Ya no repartimos más judías, ya están las estanterías llenas.")
        })
    }
    println("Lanzamos a los consumidores")
    repeat(consumidores){
        jobs.add(scope.launch {
            var stockJudias = true
            repeat(movimientos){
                if (stockJudias){
                    try {
                        delay(Random.nextLong(1, 100))
                        var retirar = Random.nextInt(5, 10)
                        println("Reparto $retirar")
                        repeat(retirar){
                            channel.send(Judias())

                        }
                    } catch (e: Exception){
                        stockJudias = false
                        println("Cliente: ya no hay stock")
                    }
                }
            }
            println("Ya no hay stock")
        })
    }
    channel.close()
    jobs.forEach {it.join()}
    println("En las estanterías quedan: ${channel.toList()}")
}

suspend fun judiasNa1Actor() {
    println("Supermercado repone judias, voy y cojo")
    val scope = CoroutineScope(Dispatchers.IO)
    val canal = Channel<Judias>(UNLIMITED)
    var job = ArrayList<Job>()
    var list = ArrayList<Judias>()

    println("Allá va Juan a por sus Judias")
    var actor = scope.actor<Judias> {
        for (judia in canal) {
            list.add(judia)
        }
    }

    println("Allá va Andrea")
    var andreaMete = 0
    var victorMete = 0
    var joseMete = 0
    var patriMete = 0

    // Andrea produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ canal.send(Judias()) }
            println("Andre mete $num Judias")
            andreaMete+=num
        }
    })

    // Victor produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){canal.send(Judias()) }
            println("Vítor mete $num Judias")
            victorMete+=num
        }
    })

    // Jose produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ canal.send(Judias()) }
            println("Jose mete $num Judias")
            joseMete+=num
        }
    })

    // Patri produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ canal.send(Judias()) }
            println("Patri mete $num Judias")
            patriMete+=num
        }
    })

    // Sincronización los trabajos
    job.forEach {it.join()}
    canal.close()

    println("Al final Andrea: $andreaMete, Victor: $victorMete, Jose da: $joseMete, Patri da: $patriMete")
    println("Juan recibe: ${list.size}$list")
}

suspend fun judiasMuchosAUno() {
    println("Reponedores reponen, cliente consume")
    val scope = CoroutineScope(Dispatchers.IO)
    val channel = Channel<Judias>(UNLIMITED)
    var job = ArrayList<Job>()

    println("Allá va Andrea")
    var andreaMete = 0
    var victorMete = 0
    var joseMete = 0
    var patriMete = 0

    // Andrea produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ channel.send(Judias()) }
            println("Andre mete $num Judias")
            andreaMete+=num
        }
    })

    // Victor produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){channel.send(Judias()) }
            println("Vítor mete $num Judias")
            victorMete+=num
        }
    })

    // Jose produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ channel.send(Judias()) }
            println("Jose mete $num Judias")
            joseMete+=num
        }
    })

    // Patri produce
    job.add(scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ channel.send(Judias()) }
            println("Patri mete $num Judias")
            patriMete+=num
        }
    })

    var list1 = ArrayList<Judias>()
    println("Juan allá va!")
    var jobJuan = scope.launch {
        var channelVacioOpen = true
        while (channelVacioOpen){
            try {
                list1.add(channel.receive())
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
        println("El cliente recibe todas las Judias")
    }

    job.forEach {it.join()}
    channel.close()
    println("Al final Andrea: $andreaMete, Victor: $victorMete, Jose da: $joseMete, Patri da: $patriMete")
    println("Juan recibe: ${list1.size}$list1")
}

suspend fun judiasNMChannel(){
    println(", cliente recibe todas las judías")
    val scope = CoroutineScope(Dispatchers.IO)
    val channel = Channel<Judias>(UNLIMITED)
    var job = ArrayList<Job>()

    println("Allá va Andrea")
    var andreaMete = 0
    var andreaJob = scope.launch {
        repeat(10){
            val num = (1..10).random()
            repeat(num){ channel.send(Judias()) }
            println("Andre mete $num Judias")
            andreaMete+=num
        }
        println("Andrea ya se ha cansado")
    }

    var list1 = ArrayList<Judias>()
    var list2 = ArrayList<Judias>()
    var list3 = ArrayList<Judias>()

    println("Lanzamos al resto")
    job.add(scope.launch {
        var channelVacioOpen = true
        while(channelVacioOpen){
            try {
                list1.add(channel.receive())
                println("Lanzamos client1")
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
    })
    job.add(scope.launch {
        var channelVacioOpen = true
        while(channelVacioOpen){
            try {
                list2.add(channel.receive())
                println("Lanzamos client2")
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
    })
    job.add(scope.launch {
        var channelVacioOpen = true
        while(channelVacioOpen){
            try {
                list3.add(channel.receive())
                println("Lanzamos client3")
            } catch (e: Exception){
                channelVacioOpen = false
            }
        }
    })

    // Sincronización los trabajos
    job.forEach {it.join()}
    println("Al final Reponedor, repone: $andreaMete, client1: ${list1.size}, client2: ${list2.size}, client3: ${list3.size}")
    println("Client 1 recibe: ${list1.size}$list1")
    println("Client 2 recibe: ${list2.size}$list2")
    println("Client 3 recibe: ${list3.size}$list3")
}

suspend fun judiasChannel(){
    println("Reponedores reponen, atracan tienda")
    val scope = CoroutineScope(Dispatchers.IO)
    val channel = Channel<Judias>(UNLIMITED)
    var jobs = ArrayList<Job>()
    var jobAtracador = ArrayList<Job>()

    repeat(4){
        var pana = it
        jobs.add(scope.launch {
            repeat(10){
                delay(Random.nextLong(1,100))
                var aded = Random.nextInt(5,10)
                repeat(aded){channel.send(Judias()) }
                println("Soy el pana $pana y es my $it vez qyue doy, ahora doy $aded, en la estantería queda $channel")
            }
            println("Soy el pana $pana, hasta luego maricarmen")
        })
    }

    repeat(2){
        var atracador = it
        jobAtracador.add(scope.launch {
            repeat(10){
                delay(Random.nextLong(1,100))
                var ret = Random.nextInt(1,5)
                repeat(ret){channel.receive() }
                println("Soy el atracador $atracador y es my $it vez qyue doy, ahora doy $channel")
            }
            println("Soy el atracador $atracador, hasta luego maricarmen")
        })
    }

    jobs.forEach {it.join()}
    println("Los atracadores corren de los pitufos")
    jobAtracador.forEach {it.join()}
    println("Los panas corren de los pitufos")
    channel.close()
    var list = channel.toList()
    println("Lanzamos al resto")
    println(list)
    println("Se acabó")
}

suspend fun judiasLaunch(){
    val scope = CoroutineScope(Dispatchers.IO)
    var stock = 2
    var mutex = Mutex()

    var productores = ArrayList<Job>()
    repeat(4){
        productores.add(scope.launch {
            repeat(20){
                delay(Random.nextLong(1,100))
                mutex.withLock {
                    var into = Random.nextInt(1,7)
                    stock += into
                    println("$stock $into")
                }
            }
        })
    }

    var consumers = ArrayList<Job>()
    repeat(2){
        consumers.add(scope.launch {
            repeat(10){
                delay(Random.nextLong(1,100))
                mutex.withLock {
                    while (stock <1){
                        println("Voy a esperar que no hay judias nene")
                    }
                    var atacau = Random.nextInt(0,stock)
                    println("Stock despues de atracar $stock")
                    println("Robo judias $atacau queda un stock de $stock")
                }
            }
        })
    }
    productores.forEach {it.join()}
    consumers.forEach {it.join()}
    println("judias a favor en stock de $stock")
}