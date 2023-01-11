package es.drodriguez.com.listviewapi.controller;

import es.drodriguez.com.listviewapi.models.Producto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class MainViewController {
    @FXML
    private ListView<Producto> listView;
    @FXML
    private Button btnActualizar;

    @FXML
    protected void updateListView() {
        // Creo el cliente
        HttpClient client = HttpClient.newHttpClient();
        //Creo la petición
        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create("https://fakestoreapi.com/products")
        ).build();

        //Cliente envía la petición
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(respons -> {
                    if (respons.statusCode() == 200) {
                        JSONArray dataArray = new JSONArray(respons.body());
                        //nos da un array
                        System.out.println(respons.body());

                        //desgranamos el array en objetos
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject data = dataArray.getJSONObject(i);
                            var producto = new Producto();
                            producto.setId(data.getInt("id"));
                            producto.setName(data.getString("title"));
                            producto.setCategory(data.getString("category"));
                            producto.setDescription(data.getString("description"));
                            producto.setImage(data.getString("image"));
                            producto.setPrice(data.getDouble("price"));
                            listView.getItems().add(producto);
                        }

                        Platform.runLater(() -> {
                            btnActualizar.setDisable(false);
                        });
                        printStatistics();

                    }
                });
    }

    private void printStatistics() {
        System.out.println("Productos: " + listView.getItems().stream().count());
        System.out.println("Categorías: " + listView.getItems().stream().map(Producto::getCategory).distinct().count());
        System.out.println("Categorías: " + listView.getItems().stream().collect(Collectors.groupingBy(x -> x.getCategory(), Collectors.counting())));
        listView.getItems().stream().map(Producto::getCategory).distinct().forEach(category -> {
            System.out.println("Categoría: " + category + " tiene " + listView.getItems().stream().filter(x -> x.getCategory().equals(category)).count() + " productos");
        });
    }
}