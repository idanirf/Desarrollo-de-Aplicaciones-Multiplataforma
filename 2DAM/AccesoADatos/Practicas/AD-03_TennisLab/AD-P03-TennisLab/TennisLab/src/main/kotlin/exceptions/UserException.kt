package exceptions

class UserException(message: String) : RuntimeException(message)
class UserControllerException(message: String) : RuntimeException(message)

class UserCachedException(message: String) : RuntimeException(message)