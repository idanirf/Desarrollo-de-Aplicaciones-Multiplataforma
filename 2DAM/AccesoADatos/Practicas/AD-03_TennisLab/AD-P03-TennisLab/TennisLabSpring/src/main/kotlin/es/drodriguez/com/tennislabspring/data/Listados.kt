package es.drodriguez.com.tennislabspring.data

import dto.TareaDto
import es.drodriguez.com.tennislabspring.dto.PedidoDto
import es.drodriguez.com.tennislabspring.dto.ProductoDto
import es.drodriguez.com.tennislabspring.models.*

object Listados {

    var asignaciones= mutableListOf<AsignacionesEncordadores>()
    var productos= mutableListOf<ProductoDto>()
    var servicios = mutableListOf<TareaDto>()
    var pedidosCompletados = mutableListOf<PedidoDto>()
    var pedidosPendientes = mutableListOf<PedidoDto>()
    var tareasCreadasEncordar = mutableListOf<TareaEncordado>()
    var tareasCreadasPersonalizar = mutableListOf<TareaPersonalizacion>()
}