package org.koin.ksp.generated

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.*

fun KoinApplication.defaultModule() = modules(defaultModule)
val defaultModule = module {
	single(qualifier=org.koin.core.qualifier.StringQualifier("TennisLabController")) { controllers.TennisLabController(get(qualifier=org.koin.core.qualifier.StringQualifier("UserRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("UserRepositoryCache")),get(qualifier=org.koin.core.qualifier.StringQualifier("TurnoRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("TareaPersonalizacionRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("TareaEncordadoRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("PedidoRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("KtorfitRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("ProductoRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("MaquinaPersonalizarRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("MaquinaEncordarRepository"))) } 
	single(qualifier=org.koin.core.qualifier.StringQualifier("UserRepositoryCache")) { repositories.cache.UserRepositoryCache(get(qualifier=org.koin.core.qualifier.StringQualifier("CacheClient"))) } bind(repositories.cache.IUserRepositoryCached::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("KtorfitRepository")) { repositories.ktorfit.KtorfitRepository() } bind(repositories.ktorfit.IKtorfitRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("MaquinaEncordarRepository")) { repositories.mongo.MaquinaEncordarRepository() } bind(repositories.mongo.IMaquinaEncordarRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("MaquinaPersonalizarRepository")) { repositories.mongo.MaquinaPersonalizarRepository() } bind(repositories.mongo.IMaquinaPersonalizarRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("PedidoRepository")) { repositories.mongo.PedidoRepository() } bind(repositories.mongo.IPedidoRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("ProductoRepository")) { repositories.mongo.ProductoRepository() } bind(repositories.mongo.IProductoRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("TareaEncordadoRepository")) { repositories.mongo.TareaEncordadoRepository() } bind(repositories.mongo.ITareaEncordado::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("TareaPersonalizacionRepository")) { repositories.mongo.TareaPersonalizacionRepository() } bind(repositories.mongo.ITareaPersonalizacion::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("TurnoRepository")) { repositories.mongo.TurnoRepository() } bind(repositories.mongo.ITurnoRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("UserRepository")) { repositories.mongo.UserRepository() } bind(repositories.mongo.IUsersRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("CacheClient")) { services.cache.CacheClient() } 
	single(qualifier=null) { view.TennisLabView(get(qualifier=org.koin.core.qualifier.StringQualifier("TennisLabController"))) } 
}