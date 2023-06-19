package controller

import Errors.AlumnosErrors
import Errors.AsignaturasErrors
import com.fasterxml.jackson.annotation.JsonTypeInfo.As
import com.github.michaelbull.result.*
import com.github.michaelbull.result.Ok
import models.Alumno
import models.Asignatura
import repositories.alumno.AlumnoRepository
import repositories.asignatura.AsignaturaRepository
import services.CacheService

class SystemController(
    private val alumnosRepository: AlumnoRepository,
    private val asignaturaRepository: AsignaturaRepository,
) {
    fun findAllAlumnos(): List<Alumno> {
        var alumnos = alumnosRepository.findAll()
        alumnos.forEach {CacheService.cacheAlumno.put(it.id, it)}
        return alumnos
    }

    fun findByIdAlumno(id: String): Result<Alumno?, AlumnosErrors.MissingAlumnos> {
        var alumno = CacheService.cacheAlumno.get(id)
        if (alumno != null) {
            alumno = alumnosRepository.findById(alumno.id)
            if (alumno!= null) {
                return Ok(alumno)
            }
            if (alumno != null) {
                return Err(AlumnosErrors.MissingAlumnos("Alumno ${alumno.id} no encontrado."))
            }
        }
        return Ok(alumno)
    }

    fun save(alumno: Alumno): Result<Alumno, AlumnosErrors> {
        var res: Result<Alumno, AlumnosErrors>
        if (alumnosRepository.findById(alumno.id) != null) {
            res = Err(AlumnosErrors.InvalidAlumnos(alumno.id))
        } else {
            val savedAlumno = alumnosRepository.save(alumno)
            CacheService.cacheAlumno.put(savedAlumno.id, savedAlumno)
            if (savedAlumno != null) {
                res = Ok(savedAlumno)
            } else {
                res = Err(AlumnosErrors.MissingAlumnos("Alumno ${alumno.id} no encontrado."))
            }
        }
        return res
    }


    fun delete(alumno: Alumno): Result<Alumno, AlumnosErrors> {
        val result: Result<Alumno, AlumnosErrors>
        if (alumnosRepository.findById(alumno.id) != null) {
            alumnosRepository.delete(alumno)
            CacheService.cacheAlumno.invalidate(alumno.id)
            result = Ok(Alumno(alumno.id))
        } else {
            result = Err(AlumnosErrors.MissingAlumnos("Alumno ${alumno.id} no encontrado."))
        }
        return result
    }


    fun findAllAsignaturas(): List<Asignatura> {
        var asignaturas = asignaturaRepository.findAll()
        return asignaturas
    }


    fun findByIdAsignaturas(): Result<Asignatura, AsignaturasErrors> {
        val asignaturas = asignaturaRepository.findAll()

        return if (asignaturas.isNotEmpty()) {
            Ok(asignaturas[0])
        } else {
            Err(AsignaturasErrors.AsignaturasMissing("Asignatura ${asignaturaRepository.findAll()[0].id} no"))
        }
    }

    fun save(asignatura: Asignatura): Result<Asignatura, AsignaturasErrors> {
        val savedAsignatura = asignaturaRepository.save(asignatura)

        return if (savedAsignatura != null) {
            Ok(savedAsignatura)
        } else {
            Err(AsignaturasErrors.AsignaturasNotSaved("Error al guardar la asignatura."))
        }
    }

    fun delete(asignatura: Asignatura): Result<Asignatura, AsignaturasErrors> {
        try {
            asignaturaRepository.delete(asignatura)
            return Ok(Asignatura(asignatura.id))
        } catch (e: Exception) {
            return Err(AsignaturasErrors.AsignaturasInvalid("Error al eliminar la asignatura."))
        }
    }
}