package Errors

sealed class AsignaturasErrors(val message: String) {
    class AsignaturasError(message: String) : AsignaturasErrors(message)
    class AsignaturasNotSaved(message: String) : AsignaturasErrors(message)
    class AsignaturasNotFound(message: String) : AsignaturasErrors(message)
    class AsignaturasInvalid(message: String) : AsignaturasErrors(message)
    class AsignaturasMissing(message: String) : AsignaturasErrors(message)
}