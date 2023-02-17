package es.drodriguez.com.tennislabspring.services.rest

import de.jensklingenberg.ktorfit.http.*
import dto.*


interface KtorFitRest {
 @GET("users")
 suspend fun getAll(@Query("page") page: Int = 0, @Query("per_page") perPage: Int = 0): List<UserDto>

 @GET("todos")
 suspend fun getAllTareas(@Query("page") page: Int = 0, @Query("per_page") perPage: Int = 0): List<TareaDto>

 @POST("todos")
 suspend fun create(@Body usuario: TareaDto): TareaDto

 @PUT("todos/{id}")
 suspend fun update(@Path("id") id: String, @Body usuario: TareaDto): TareaDto

 @PATCH("todos/{id}")
 suspend fun upgrade(@Path("id") id: Long, @Body usuario: TareaDto): TareaDto

 @DELETE("todos/{id}")
 suspend fun delete(@Path("id") id: String): TareaDto

}