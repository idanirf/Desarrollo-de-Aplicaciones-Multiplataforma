package exceptions

class TurnoException(message: String) : RuntimeException(message)
class TurnoControllersException(message: String) : RuntimeException(message)

class TurnoCachedException(message: String) : RuntimeException(message)