package es.drodriguez.itvh2hibernatespringv2.models

import jakarta.persistence.*


@Entity
@Table(name = "owner")
@NamedQuery(name = "Owner.findAll", query = "SELECT t FROM Owner t")
class Owner(
    @Id
    var uuidOwner: String? = null,
    var dni: String? = null,
    var name: String? = null,
    var surnames: String? = null,
    var telf: String? = null
) {
    override fun toString(): String {
        return "Owner(uuidOwner=$uuidOwner, dni=$dni, name=$name, surnames=$surnames, telf=$telf)"
    }
}