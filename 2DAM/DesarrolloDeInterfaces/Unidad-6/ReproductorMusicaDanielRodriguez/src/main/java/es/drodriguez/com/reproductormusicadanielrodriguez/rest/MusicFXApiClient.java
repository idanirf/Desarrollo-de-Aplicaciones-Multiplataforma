package es.drodriguez.com.reproductormusicadanielrodriguez.rest;

import es.drodriguez.com.reproductormusicadanielrodriguez.models.Song;
import es.drodriguez.com.reproductormusicadanielrodriguez.utils.Config;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MusicFXApiClient {
    private static MusicFXApiService apiStoreService;

    public MusicFXApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiStoreService = retrofit.create(MusicFXApiService.class);
    }

    public static List<Song> getDataSongs() throws InterruptedException, IOException {

        Call<List<Song>> songsCall = apiStoreService.getSongsAll();
        return songsCall.execute().body();
    }

    //Asynchronous
    public CompletableFuture<List<Song>> getSongsAsync(int limit, String sort) {
        return CompletableFuture.supplyAsync(() -> {
            List<Song> songs = null;
            try {
                apiStoreService.getSongs(limit, sort).execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return songs;
        });
    }

    //Synchronous
    public List<Song> getSongs(int limit, String sort) {
        List<Song> songs = null;
        try {
            apiStoreService.getSongs(limit, sort).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

}
