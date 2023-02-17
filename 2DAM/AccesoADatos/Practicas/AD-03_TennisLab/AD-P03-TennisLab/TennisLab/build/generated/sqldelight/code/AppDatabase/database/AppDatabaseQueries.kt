package database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> selectUsers(mapper: (
    _id: String,
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String
  ) -> T): Query<T>

  public fun selectUsers(): Query<User>

  public fun <T : Any> selectByIdUser(_id: String, mapper: (
    _id: String,
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String
  ) -> T): Query<T>

  public fun selectByIdUser(_id: String): Query<User>

  public fun removeAllUsers(): Unit

  public fun insertUser(
    _id: String,
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String
  ): Unit

  public fun updateUser(
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String,
    _id: String
  ): Unit

  public fun deleteUser(_id: String): Unit
}
