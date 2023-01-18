package es.drodriguez.com.visorproductosdanielrodriguez.controller;

import es.drodriguez.com.visorproductosdanielrodriguez.models.Producto;
import es.drodriguez.com.visorproductosdanielrodriguez.rest.FakeApi;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainViewController implements Initializable {
    @FXML
    private Button btnActualizar;

    @FXML
    private TextField categoriaArea;

    @FXML
    private Label categoriaLabel;

    @FXML
    private TextArea descripcionArea;

    @FXML
    private Label descripcionLabel;

    @FXML
    private ImageView imagenArea;

    @FXML
    private ListView<Producto> listView;

    @FXML
    private TextField nombreArea;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField precioArea;

    @FXML
    private Label precioLabel;

    @FXML
    private Label tituloLabel;
    @FXML
    private PieChart grafico;
    private FakeApi service;
    private List<Producto> products;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FakeApi.class);
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nombreArea.setText(newValue.getTitle());
                precioArea.setText(String.valueOf(newValue.getPrice()));
                descripcionArea.setText(newValue.getDescription());
                categoriaArea.setText(newValue.getCategory());
                Image image = new Image(newValue.getImage());
                imagenArea.setImage(image);
            }
        });
    }

    @FXML
    void updateListView() {
        btnActualizar.setDisable(true);
        Call<List<Producto>> productsCall = service.getProductsAll();

        productsCall.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    products = response.body();
                    Platform.runLater(() -> {
                        listView.getItems().removeAll();
                        listView.getItems().addAll(products);
                        btnActualizar.setDisable(false);
                        initializeGrafico();
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @FXML
    public void initializeGrafico() {
        // Creamos un mapa para almacenar los datos

        var nombresProductos = products.stream().distinct();
        // Mapa por categoria
        var mapa = nombresProductos.collect(Collectors.groupingBy(Producto::getCategory, Collectors.counting()));
        // Creamos una lista de datos para el gráfico
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        // Añadimos los datos al gráfico
        mapa.entrySet().forEach(entry -> pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue())));

        grafico.setData(pieChartData);
        grafico.setClockwise(false);
        grafico.setTitle("Nº Productos por categoría");
    }
}