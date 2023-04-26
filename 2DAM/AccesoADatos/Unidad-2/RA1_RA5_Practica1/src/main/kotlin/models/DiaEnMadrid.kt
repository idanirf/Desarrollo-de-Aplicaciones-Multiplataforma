package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "DiaMadrid")
class DiaEnMadrid(
    @field:Element(name = "fecha")
    @param:Element(name = "fecha")
    var fecha: String = "",
    @field:Element(name = "DatosInforme")
    @param:Element(name = "DatosInforme")
    var datosInforme: ListaDatosMadrid?,

)