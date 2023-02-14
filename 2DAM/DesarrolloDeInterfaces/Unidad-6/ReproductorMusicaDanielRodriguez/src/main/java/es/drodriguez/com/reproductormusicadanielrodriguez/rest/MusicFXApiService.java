package es.drodriguez.com.reproductormusicadanielrodriguez.rest;

import es.drodriguez.com.reproductormusicadanielrodriguez.models.Album;
import es.drodriguez.com.reproductormusicadanielrodriguez.models.Artist;
import es.drodriguez.com.reproductormusicadanielrodriguez.models.Song;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface MusicFXApiService {
    @GET("/artists")
    Call<List<Artist>> getArtists(@Query("limit") int limit, @Query("sort") String sort);

    @GET("/artists")
    Call<List<Artist>> getArtistsAll();

    @GET("/songs")
    Call<List<Song>> getSongs(@Query("limit") int limit, @Query("sort") String sort);

    @GET("/songs")
    Call<List<Song>> getSongsAll();

    @GET("/albums")
    Call<List<Album>> getAlbums(@Query("limit") int limit, @Query("sort") String sort);
}
