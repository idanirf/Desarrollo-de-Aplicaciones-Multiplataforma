package repositories.users

import com.mongodb.assertions.Assertions
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.cache.UserRepositoryCache
import services.cache.CacheClient
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryCacheTest {
    val userRepository = UserRepositoryCache(CacheClient())


    val user = User(
        _id = "21",
        id = "55",
        name = "Jorge",
        username = "Juan",
        email = "juan@gmail.com",
        password = "hola",
        tipoUser = User.TipoUsuario.ADMIN,
        phone = "736363736",
        website = "http://www.juan"
    )

    @Test
    fun findAll()  = runBlocking {
        userRepository.save(user)
        val list = userRepository.findAll().toList()
        assertAll(
            { Assertions.assertNotNull(list) },
            { assertEquals(1, list.size) }
        )
        userRepository.delete(user._id)
    }

    // Test del findById de usuarios
    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        val users = userRepository.findById("21")
        assertAll(
            { Assertions.assertNotNull(user) },
            { assertEquals("21", users?._id) }
        )
        userRepository.delete(user._id)
    }

    // Test del save de usuarios
    @Test
    fun save() = runBlocking {
        userRepository.save(user)
        val users = userRepository.findById("21")
        assertAll(
            { Assertions.assertNotNull(user) },
            { assertEquals("21", user._id) }
        )
        userRepository.delete(user._id)
    }

    // Test del update de usuarios
    @Test
    fun update() = runBlocking {
        userRepository.save(user)
        userRepository.update(user)
        val users = userRepository.findById("21")
        assertAll(
            { Assertions.assertNotNull(user) },
            { assertEquals("21", user._id) }
        )
        userRepository.delete(user._id)

    }

    // Test del delete de usuarios
    @Test
    fun delete() = runBlocking {
        userRepository.save(user)
        val userr = userRepository.findById(user._id)
        userRepository.delete(user._id)

        val userrr = userRepository.findAll().toList()


        assertAll(
            { Assertions.assertNotNull(userr) },
            { assertEquals(0, userrr.size) }
        )
    }
}