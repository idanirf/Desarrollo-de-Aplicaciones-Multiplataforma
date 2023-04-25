package cache

import io.github.reactivecircus.cache4k.Cache
import database.VehiculoDto

object VehiculoCacheController {
    val isRefresco: Boolean = false
    val tiempoRefresco = 60 * 60 * 1000L // 1 hora en milisegundos
    val tamanioCache = 100
    val cache = Cache.Builder().maximumCacheSize(tamanioCache.toLong()).build<Long, VehiculoDto>()
}