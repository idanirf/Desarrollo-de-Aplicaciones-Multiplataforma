package exceptions

class PedidoException(message: String) : RuntimeException(message)
class PedidoControllerException(message: String) : RuntimeException(message)

class PedidoCachedException(message: String) : RuntimeException(message)