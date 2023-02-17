package exceptions

class MaquinaEncordarException(message: String) : RuntimeException(message)
class MaquinaEncordarControllerException(message: String) : RuntimeException(message)

class MaquinaEncordarCachedException(message: String) : RuntimeException(message)