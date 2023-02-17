package exceptions

class MaquinaPersonalizarException(message: String) : RuntimeException(message)
class MaquinaPersonalizarControllerException(message: String) : RuntimeException(message)

class MaquinaPersonalizarCachedException(message: String) : RuntimeException(message)