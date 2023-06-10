package es.drodriguez.itvh2hibernatespringv2.errors

sealed class EmployeeErrors(val message: String) {
    class EmployeeNotValid(message: String) : EmployeeErrors(message)
    class EmployeeNotFound(message: String) : EmployeeErrors(message)
    class EmployeeNotSaved(message: String) : EmployeeErrors(message)
}