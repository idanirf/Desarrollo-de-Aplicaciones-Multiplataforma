package services

import io.github.reactivecircus.cache4k.Cache
import models.Alumno

object CacheService {
    val isRefresco: Boolean = false
    val tiempoRefresco = 60 * 60 * 1000L
    val tamanioCache = 100
    val cacheAlumno = Cache.Builder().maximumCacheSize(tamanioCache.toLong()).build<String, Alumno>()
}