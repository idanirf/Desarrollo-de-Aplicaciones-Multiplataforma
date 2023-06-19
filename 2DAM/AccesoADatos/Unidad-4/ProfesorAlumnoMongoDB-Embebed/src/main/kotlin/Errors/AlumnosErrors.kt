package Errors

sealed class AlumnosErrors(val message: String) {
    class InvalidAlumnos(message: String) : AlumnosErrors(message)
    class MissingAlumnos(message: String) : AlumnosErrors(message)
    class NotSavedAlumnos(message: String) : AlumnosErrors(message)
    class NotFoundAlumnos(message: String) : AlumnosErrors(message)
}