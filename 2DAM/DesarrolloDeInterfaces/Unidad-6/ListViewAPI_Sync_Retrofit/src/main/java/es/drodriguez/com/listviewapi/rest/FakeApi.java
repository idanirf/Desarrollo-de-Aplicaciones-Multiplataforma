package es.drodriguez.com.listviewapi.rest;

import es.drodriguez.com.listviewapi.models.Producto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface FakeApi {
    @GET("/products")
    Call<List<Producto>> getProducts(@Query("limit") int limit, @Query("sort") String sort);

    @GET("/products")
    Call<List<Producto>> getProductsAll();

    @GET("/products/{id}")
    Call<Producto> getProduct(@Path("id") int id);
}
