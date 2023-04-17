package org.koin.ksp.generated

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.*

fun KoinApplication.defaultModule() = modules(defaultModule)
val defaultModule = module {
	single(qualifier=null) { controllers.EmpleadoController(get()) } 
	single(qualifier=null) { repositories.EmpleadoRepositoryImplementado() } bind(repositories.EmpleadoRepository::class)
}