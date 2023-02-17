package data

import dto.TareaDto
import models.*

object Listados {

    var asignaciones= mutableListOf<AsignacionesEncordadores>()
    var productos= mutableListOf<Producto>()
    var servicios = mutableListOf<TareaDto>()
    var pedidosCompletados = mutableListOf<Pedido>()
    var pedidosPendientes = mutableListOf<Pedido>()
    var tareasCreadasEncordar = mutableListOf<TareaEncordado>()
    var tareasCreadasPersonalizar = mutableListOf<TareaPersonalizacion>()
}