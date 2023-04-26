package models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Informe")
class Informe(
    @field:ElementList(entry = "DiaEnMadrid", inline = true)
    @param:ElementList(entry = "DiaEnMadrid", inline = true)
    var lista: List<DiaEnMadrid> = ArrayList<DiaEnMadrid>()
)