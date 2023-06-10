package es.drodriguez.itvh2hibernatespringv2.errors

import es.drodriguez.itvh2hibernatespringv2.models.Owner

sealed class OwnerErrors(val message: String) {
    class OwnerNotValid(message: String) : OwnerErrors(message)
    class OwnerNotFound(message: String) : OwnerErrors(message)
    class OwnerNotSaved(message: String) : OwnerErrors(message)
}