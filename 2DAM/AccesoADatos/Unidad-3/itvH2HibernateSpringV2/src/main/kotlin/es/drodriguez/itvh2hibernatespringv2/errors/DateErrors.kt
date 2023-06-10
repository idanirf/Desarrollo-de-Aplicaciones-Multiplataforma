package es.drodriguez.itvh2hibernatespringv2.errors

sealed class DateErrors(val message: String) {
    class DateNotValid(message: String) : DateErrors(message)
    class DateNotFound(message: String) : DateErrors(message)
    class DateNotSaved(message: String) : DateErrors(message)
}