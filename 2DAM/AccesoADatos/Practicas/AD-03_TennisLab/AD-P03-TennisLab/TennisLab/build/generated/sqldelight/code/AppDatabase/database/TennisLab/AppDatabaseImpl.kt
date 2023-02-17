package database.TennisLab

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import database.AppDatabase
import database.AppDatabaseQueries
import database.User
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE User (
          |  _id TEXT PRIMARY KEY,
          |  id TEXT NOT NULL,
          |  name TEXT NOT NULL,
          |  username TEXT NOT NULL,
          |  email TEXT NOT NULL,
          |  password TEXT NOT NULL,
          |  tipoUser TEXT NOT NULL,
          |  phone TEXT NOT NULL,
          |  website TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val selectUsers: MutableList<Query<*>> = copyOnWriteList()

  internal val selectByIdUser: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> selectUsers(mapper: (
    _id: String,
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String
  ) -> T): Query<T> = Query(-1690150335, selectUsers, driver, "AppDatabase.sq", "selectUsers",
      "SELECT * FROM User") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!
    )
  }

  public override fun selectUsers(): Query<User> = selectUsers { _id, id, name, username, email,
      password, tipoUser, phone, website ->
    User(
      _id,
      id,
      name,
      username,
      email,
      password,
      tipoUser,
      phone,
      website
    )
  }

  public override fun <T : Any> selectByIdUser(_id: String, mapper: (
    _id: String,
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String
  ) -> T): Query<T> = SelectByIdUserQuery(_id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!
    )
  }

  public override fun selectByIdUser(_id: String): Query<User> = selectByIdUser(_id) { _id_, id,
      name, username, email, password, tipoUser, phone, website ->
    User(
      _id_,
      id,
      name,
      username,
      email,
      password,
      tipoUser,
      phone,
      website
    )
  }

  public override fun removeAllUsers(): Unit {
    driver.execute(-1859102314, """DELETE FROM User""", 0)
    notifyQueries(-1859102314, {database.appDatabaseQueries.selectUsers +
        database.appDatabaseQueries.selectByIdUser})
  }

  public override fun insertUser(
    _id: String,
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String
  ): Unit {
    driver.execute(-1450271825, """
    |INSERT INTO User (_id, id, name, username, email,password,tipoUser, phone, website)
    |VALUES(?, ?, ?, ?, ?,?,?, ?, ?)
    """.trimMargin(), 9) {
      bindString(1, _id)
      bindString(2, id)
      bindString(3, name)
      bindString(4, username)
      bindString(5, email)
      bindString(6, password)
      bindString(7, tipoUser)
      bindString(8, phone)
      bindString(9, website)
    }
    notifyQueries(-1450271825, {database.appDatabaseQueries.selectUsers +
        database.appDatabaseQueries.selectByIdUser})
  }

  public override fun updateUser(
    id: String,
    name: String,
    username: String,
    email: String,
    password: String,
    tipoUser: String,
    phone: String,
    website: String,
    _id: String
  ): Unit {
    driver.execute(1582598591,
        """UPDATE User SET id = ?, name = ?, username = ?, email = ?,password = ?,tipoUser = ?, phone = ?, website = ? WHERE _id = ?""",
        9) {
      bindString(1, id)
      bindString(2, name)
      bindString(3, username)
      bindString(4, email)
      bindString(5, password)
      bindString(6, tipoUser)
      bindString(7, phone)
      bindString(8, website)
      bindString(9, _id)
    }
    notifyQueries(1582598591, {database.appDatabaseQueries.selectUsers +
        database.appDatabaseQueries.selectByIdUser})
  }

  public override fun deleteUser(_id: String): Unit {
    driver.execute(-651848287, """DELETE FROM User WHERE _id = ?""", 1) {
      bindString(1, _id)
    }
    notifyQueries(-651848287, {database.appDatabaseQueries.selectUsers +
        database.appDatabaseQueries.selectByIdUser})
  }

  private inner class SelectByIdUserQuery<out T : Any>(
    public val _id: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectByIdUser, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(94052324,
        """SELECT * FROM User WHERE _id = ?""", 1) {
      bindString(1, _id)
    }

    public override fun toString(): String = "AppDatabase.sq:selectByIdUser"
  }
}
