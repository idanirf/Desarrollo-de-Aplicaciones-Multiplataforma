package es.drodriguez.com.listviewapi.controller;

import es.drodriguez.com.listviewapi.models.Producto;
import es.drodriguez.com.listviewapi.rest.FakeApi;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class MainViewController {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnActualizar;

    private FakeApi service;

    public MainViewController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FakeApi.class);
    }
    @FXML
    protected void updateListView() {
        btnActualizar.setDisable(true);
        Call<List<Producto>> productsCall = service.getProductsAll();

        productsCall.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> products = response.body();
                    Platform.runLater(() -> {
                        listView.getItems().removeAll();
                        listView.getItems().addAll(products);
                        btnActualizar.setDisable(false);
                        printStatistics();
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void printStatistics() {

        System.out.println("Productos: " + listView.getItems().stream().count());
        System.out.println("Categorias: " + listView.getItems().stream().map(Producto::getCategory).distinct().count());
        System.out.println("Numero de productos por categoria: " + listView.getItems().stream().collect(Collectors.groupingBy(x -> x.getCategory(), Collectors.counting())));

        listView.getItems().stream().map(Producto::getCategory).distinct().forEach(category ->
                System.out.println("Numero de productos por categoria: " + listView.getItems().stream().filter(x -> x.getCategory().equals(category)).count()));

        DoubleSummaryStatistics estadisticas = listView.getItems().stream().mapToDouble(Producto::getPrice).summaryStatistics();
        System.out.println("Precio maximo: " + estadisticas.getMax());
        System.out.println("Precio minimo: " + estadisticas.getMin());
        System.out.println("Precio medio: " + estadisticas.getAverage());
        System.out.println("Precio total: " + estadisticas.getSum());
    }
}
