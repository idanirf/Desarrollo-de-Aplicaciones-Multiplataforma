package es.drodriguez.itvh2hibernatespringv2.errors

sealed class VehicleErrors(val message: String) {
    class VehiculoNotValid(message: String) : VehicleErrors(message)
    class VehiculoNotFound(message: String) : VehicleErrors(message)
    class VehiculoNotSaved(message: String) : VehicleErrors(message)
}