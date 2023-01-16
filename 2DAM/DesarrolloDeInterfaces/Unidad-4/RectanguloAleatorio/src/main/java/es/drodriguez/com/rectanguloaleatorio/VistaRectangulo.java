package es.drodriguez.com.rectanguloaleatorio;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaRectangulo extends BorderPane {
    private final StackPane pista;
    private final Rectangle rectangulo;


    public VistaRectangulo() {
        pista = new StackPane();
        //Creamos rectangulo con color aleatorio

        this.rectangulo = new Rectangle();
        this.rectangulo.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        this.rectangulo.widthProperty().bind(this.widthProperty().divide(2));
        this.rectangulo.heightProperty().bind(this.heightProperty().divide(2));
        //Cambiar color rectángulo al hacer click
        this.rectangulo.setOnMouseClicked(this::onRectanguloClick);
        //Redimensionar rectangulo
        this.rectangulo.setOnMouseDragged(this::onRectanguloDragged);

        this.getChildren().addAll(pista, rectangulo);


    }
    //Cambiar el tamaño del rectangulo con el raton
    private void onRectanguloDragged(MouseEvent mouseEvent) {
        this.rectangulo.widthProperty().unbind();
        this.rectangulo.heightProperty().unbind();
        var x = mouseEvent.getX();
        var y = mouseEvent.getY();
        this.rectangulo.setWidth(x);
        this.rectangulo.setHeight(y);
    }


    private void onRectanguloClick(MouseEvent mouseEvent) {
        this.rectangulo.setFill(Color.color(Math.random(), Math.random(), Math.random()));
    }
}
