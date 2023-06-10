package es.drodriguez.itvh2hibernatespringv2.errors

sealed class ReportError(val message: String) {
    class ReportNotValid(message: String) : ReportError(message)
    class ReportNotFound(message: String) : ReportError(message)
    class ReportNotSaved(message: String) : ReportError(message)
}