package repositories.alumno

import models.Alumno
import repositories.ICrudRepository

interface IAlumnoRepository: ICrudRepository<Alumno, String> {
}