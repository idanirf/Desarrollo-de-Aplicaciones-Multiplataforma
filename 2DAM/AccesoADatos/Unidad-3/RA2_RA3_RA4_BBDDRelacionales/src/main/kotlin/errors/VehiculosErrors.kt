package errors

sealed class VehiculosErrors(val message: String) {
    class VehiculoNotValid(message: String) : VehiculosErrors(message)
    class VehiculoNotFound(message: String) : VehiculosErrors(message)
    class VehiculoNotSaved(message: String) : VehiculosErrors(message)
}