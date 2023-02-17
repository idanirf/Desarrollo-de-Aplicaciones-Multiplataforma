package data

import models.*
import org.litote.kmongo.newId
import utils.Contraseñas
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Data {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")


    fun getUsers(): List<User> {
        return listOf(
            User(
                _id = "666",
                id = "55",
                name = "Jorge",
                username = "Jorgesanchez3212",
                email = "admin@gmail.com",
                password = Contraseñas.encriptar("admin"),
                tipoUser = User.TipoUsuario.ADMIN,
                phone = "736363736",
                website = "http://www.juan",

            ),
            User(
                _id = "67",
                id = "55",
                name = "Daniel",
                username = "DanielRodriguez",
                email = "cliente@gmail.com",
                password = Contraseñas.encriptar("cliente"),
                tipoUser = User.TipoUsuario.CLIENTE,
                phone = "736363736",
                website = "http://www.juan",

                ),
            User(
                _id = "53",
                id = "525",
                name = "Alfredo",
                username = "alfred",
                email = "encordador@gmail.com",
                password = Contraseñas.encriptar("encordador"),
                tipoUser = User.TipoUsuario.TRABAJADOR,
                phone = "736363736",
                website = "http://www.juan",

                ))
    }
    fun getTurnos(): List<Turno> {
        return listOf(
            Turno(
                _id = "123",
                fechaInicio =  LocalDateTime.parse("2022-02-15T00:00:00.000+05:30", formatter),
                fechaFinal = LocalDateTime.parse("2024-12-16T00:00:00.000+05:30", formatter),
                usuario_id = "666",
            ),
            Turno(
                _id = "1234",
                fechaInicio =  LocalDateTime.parse("2023-10-27T00:00:00.000+05:30", formatter),
                fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                usuario_id = "53",
            ),
            Turno(
                    _id = "12345",
            fechaInicio =  LocalDateTime.parse("2021-11-27T00:00:00.000+05:30", formatter),
            fechaFinal = LocalDateTime.parse("2024-02-27T00:00:00.000+05:30", formatter),
            usuario_id = "53",
        ))
    }

    fun getPedidos(): List<Pedido> {
        return listOf(
            Pedido(
                _id = "123456789",
                estado = Pedido.TipoEstado.EN_PROCESO,
                fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                precio = 222.2,
                usuario_id = "666",
            ),
            Pedido(
                _id = "12345678910",
                estado = Pedido.TipoEstado.TERMINADO,
                fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                precio = 222.2,
                usuario_id = "67",
            ),
            Pedido(
                _id = "12345678911",
                estado = Pedido.TipoEstado.TERMINADO,
                fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                precio = 222.2,
                usuario_id = "53",
            ))
    }

    fun getTareasPersonalizacion(): List<TareaPersonalizacion> {
        return listOf(
            TareaPersonalizacion(
                rigidez = 8.00,
                peso = 1.20,
                balance = 1.10,
                precio = 222.2,
                pedido_id = "123456789",
            ),
            TareaPersonalizacion(
                rigidez = 8.00,
                peso = 1.20,
                balance = 1.10,
                precio = 222.2,
                pedido_id = "12345678910",
            ),
            TareaPersonalizacion(
                rigidez = 8.00,
                peso = 1.20,
                balance = 1.10,
                precio = 222.2,
                pedido_id = "12345678911",
            ),
        )
    }

    fun getTareaEncordado(): List<TareaEncordado> {
        return listOf(
            TareaEncordado(
                precio = 18.00,
                tensionVertical = 11.20,
                cordajeVertical = "Cordaje Vertical",
                tensionHorizontal = 13.10,
                cordajeHorizontal = "Cordaje Horizontal",
                nudos = TareaEncordado.NumeroNudos.CUATRO,
                pedido_id = "12345678910",
            ),

            TareaEncordado(
                        precio = 8.00,
            tensionVertical = 1.20,
            cordajeVertical = "Cordaje Vertical",
            tensionHorizontal = 1.10,
            cordajeHorizontal = "Cordaje Horizontal",
            nudos = TareaEncordado.NumeroNudos.DOS,
            pedido_id = "12345678910",
        ),
            TareaEncordado(
            precio = 8.00,
            tensionVertical = 17.20,
            cordajeVertical = "Cordaje Vertical",
            tensionHorizontal = 19.10,
            cordajeHorizontal = "Cordaje Horizontal",
            nudos = TareaEncordado.NumeroNudos.CUATRO,
            pedido_id = "12345678910",
        ),
        )
    }

    fun getMaquinaEncordado(): List<MaquinaEncordar> {
        return listOf(
            MaquinaEncordar(
                marca = "Maquina marca Encordado 1",
                modelo = "Maquina modelo Encordado 1",
                fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                numeroSerie = "3345",
                tipo = MaquinaEncordar.TipoEncordaje.AUTOMATICA,
                tensionMaxima = 12.32,
                tensionMinima = 11.00,
                turno_id = "123",
            )
        )
    }

    fun getMaquinaPersonalizacion(): List<MaquinaPersonalizar> {
        return listOf(
            MaquinaPersonalizar(
                marca = "Maquina marca Personalizacion 1",
                modelo = "Maquina modelo Personalizacion 1",
                fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", formatter),
                numeroSerie = "3345",
                swingweight = "true",
                balance = 12.32,
                rigidez = 11.00,
                turno_id = "123",
            )
        )
    }

    fun getProducto(): List<Producto> {
        return listOf(
            Producto(
                marca = "Nike",
                modelo =" Babolat",
                precio = 200.6,
                stock = "3345",
                tipoProducto = Producto.TipoProducto.RAQUETA,
                pedido_id = "12345678910",

            ),
            Producto(
                marca = "Adidas",
                modelo =" Babolat",
                precio = 180.6,
                stock = "245",
                tipoProducto = Producto.TipoProducto.RAQUETA,
                pedido_id = "123456789",
                ),
            Producto(
                marca = "Siemens",
                modelo =" Negro",
                precio = 118.6,
                stock = "2458",
                tipoProducto = Producto.TipoProducto.COMPLEMENTO,
                pedido_id = "12345678911",
            )
        )
    }


}