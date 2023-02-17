package repositories.ktorfit

import com.mongodb.assertions.Assertions
import dto.TareaDto
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KtorFitRepositoryTest {
    val ktorfitRepository = KtorfitRepository()
    private val tarea = TareaDto(
        "1","10","Probando test", "true"
    )

@Test
    fun findAllUsers()  = runBlocking {
        val userdto = ktorfitRepository.findAll().toList()
        assertAll(
            { Assertions.assertNotNull(userdto) },
            { assertEquals(10, userdto.size) }
        )
    }

    @Test
    fun findAllTareas()  = runBlocking {
        val tareasdto = ktorfitRepository.findAllTareas().toList()
        assertAll(
            { Assertions.assertNotNull(tareasdto) },
            { assertEquals(200, tareasdto.size) }
        )
    }

    @Test
    fun postTareas()  = runBlocking {
        val tareadto = ktorfitRepository.create(tarea)
        assertAll(
            { Assertions.assertNotNull(tareadto) },
        )
    }
}