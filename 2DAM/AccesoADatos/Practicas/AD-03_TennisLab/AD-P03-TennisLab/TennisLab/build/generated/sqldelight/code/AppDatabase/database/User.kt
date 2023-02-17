package database

import kotlin.String

public data class User(
  public val _id: String,
  public val id: String,
  public val name: String,
  public val username: String,
  public val email: String,
  public val password: String,
  public val tipoUser: String,
  public val phone: String,
  public val website: String
) {
  public override fun toString(): String = """
  |User [
  |  _id: $_id
  |  id: $id
  |  name: $name
  |  username: $username
  |  email: $email
  |  password: $password
  |  tipoUser: $tipoUser
  |  phone: $phone
  |  website: $website
  |]
  """.trimMargin()
}
