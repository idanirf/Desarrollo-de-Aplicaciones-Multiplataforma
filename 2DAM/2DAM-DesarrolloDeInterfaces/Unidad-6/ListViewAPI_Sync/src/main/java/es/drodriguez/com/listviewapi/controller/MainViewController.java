package es.drodriguez.com.listviewapi.controller;

import es.drodriguez.com.listviewapi.models.Producto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

public class MainViewController {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnActualizar;

    @FXML
    protected void updateListView() {
        Runnable task = () -> {
            btnActualizar.setDisable(true);
            //Creo cliente
            HttpClient cliente = HttpClient.newHttpClient();
            //Creo la peticion
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://fakestoreapi.com/products")).build();

            HttpResponse<String> response = null;
            try {
                response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (response.statusCode() == 200) {
                createPorductList(response);
                printStatistics();
            }
            Platform.runLater(() ->
                    btnActualizar.setDisable(false));


        };
        new Thread(task).start();
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

    private void createPorductList(HttpResponse<String> response) {
        JSONArray dataArray = new JSONArray(response.body());
        dataArray.forEach(data -> listView.getItems().add(new Producto(
                ((JSONObject) data).getInt("id"),
                ((JSONObject) data).getString("title"),
                ((JSONObject) data).getString("category"),
                ((JSONObject) data).getDouble("price"),
                ((JSONObject) data).getString("image"),
                ((JSONObject) data).getString("description")
        )));
    }
}
