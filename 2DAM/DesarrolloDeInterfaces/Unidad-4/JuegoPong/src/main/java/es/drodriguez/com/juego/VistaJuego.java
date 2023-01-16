package es.drodriguez.com.juego;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VistaJuego extends BorderPane {
    private final StackPane pista;
    private final Rectangle paredSuperior;
    private final Rectangle paredInferior;
    private final Text titulo;
    private final Text jugador2;
    private final Text puntuacion1;
    private final Text puntuacion2;
    private final Button empezar;
    private Rectangle raquetaDerecha;
    private Rectangle raquetaIzquierda;
    private Circle pelota;
    private Timeline animacion;

    private double desplazamientoX;
    private double desplazamientoY;

    private KeyValue letraW;
    private Button teclado;

    public VistaJuego() {
        pista = new StackPane();

        desplazamientoX = 1;
        desplazamientoY = 1;
        this.raquetaIzquierda = new Rectangle();
        this.raquetaDerecha = new Rectangle();

        this.paredSuperior = new Rectangle();
        this.paredInferior = new Rectangle();

        this.pelota = new Circle();

        this.raquetaDerecha.setFill(Color.BLACK);
        this.raquetaIzquierda.setFill(Color.BLACK);

        this.paredSuperior.setFill(Color.BLACK);
        this.paredInferior.setFill(Color.BLACK);

        this.pelota.setFill(Color.BLACK);

        this.titulo = new Text("TenisFX");
        this.titulo.setFill(Color.BLACK);
        this.titulo.setStyle("-fx-font-size: 16px;");

        Text jugador1 = new Text("JUGADOR 1 ");
        jugador1.setFill(Color.BLACK);
        jugador1.setStyle("-fx-font-size: 12px;");

        this.jugador2 = new Text("JUGADOR 2 ");
        this.jugador2.setFill(Color.BLACK);
        this.jugador2.setStyle("-fx-font-size: 12px;");

        this.puntuacion1 = new Text("0");
        this.puntuacion1.setFill(Color.BLACK);
        this.puntuacion1.setStyle("-fx-font-size: 12px;");

        this.puntuacion2 = new Text("0");
        this.puntuacion2.setFill(Color.BLACK);
        this.puntuacion2.setStyle("-fx-font-size: 12px;");

        pista.setFocusTraversable(true);
        pista.requestFocus();
        pista.setOnKeyPressed(event -> moverTecla(event));


        this.empezar = new Button("Empezar");
        this.empezar.setOnAction(e -> {
            this.empezar.setVisible(false);
            lanzarJuego();
        });


        //Raquetas
        this.raquetaIzquierda.translateXProperty().bind(pista.widthProperty().divide(-2.2));
        this.raquetaIzquierda.heightProperty().bind(pista.widthProperty().divide(5));
        this.raquetaIzquierda.widthProperty().bind(pista.widthProperty().divide(25));
        this.raquetaDerecha.translateXProperty().bind(pista.widthProperty().divide(2.2));
        this.raquetaDerecha.heightProperty().bind(pista.widthProperty().divide(5));
        this.raquetaDerecha.widthProperty().bind(pista.widthProperty().divide(25));

        //Pelota
        this.pelota.radiusProperty().bind(pista.widthProperty().divide(50));

        //Paredes (Superior e inferior)
        this.paredSuperior.translateYProperty().bind(pista.heightProperty().divide(-2.6));
        this.paredSuperior.widthProperty().bind(pista.widthProperty().divide(1.1));
        this.paredSuperior.heightProperty().bind(pista.widthProperty().divide(25));


        this.paredInferior.translateYProperty().bind(pista.heightProperty().divide(2.8));
        this.paredInferior.widthProperty().bind(pista.widthProperty().divide(1.1));
        this.paredInferior.heightProperty().bind(pista.widthProperty().divide(25));

        //Titulo
        this.titulo.translateYProperty().bind(pista.heightProperty().divide(-2.2));

        //Marcadores Jugadores
        jugador1.translateYProperty().bind(pista.widthProperty().divide(3.0));
        jugador1.translateXProperty().bind(pista.widthProperty().divide(-2.5));

        this.jugador2.translateYProperty().bind(pista.widthProperty().divide(3.0));
        this.jugador2.translateXProperty().bind(pista.heightProperty().divide(1.9));

        this.puntuacion1.translateYProperty().bind(pista.widthProperty().divide(2.8));
        this.puntuacion1.translateXProperty().bind(pista.heightProperty().divide(-1.7));

        this.puntuacion2.translateYProperty().bind(pista.widthProperty().divide(2.8));
        this.puntuacion2.translateXProperty().bind(pista.heightProperty().divide(1.7));

        //Botón Empezar
        this.empezar.translateYProperty().bind(pista.heightProperty().divide(10.0));
        this.empezar.translateXProperty().bind(pista.widthProperty().divide(50));


        pista.getChildren().addAll(raquetaIzquierda, raquetaDerecha, pelota, paredSuperior, paredInferior, titulo,
                jugador1, jugador2, puntuacion1, puntuacion2, empezar);
        this.setCenter(pista);
    }

    //LOGICA DEL JUEGO
    private void lanzarJuego() {
        animacion = new Timeline(new KeyFrame(Duration.millis(17), k -> {
            moverPelota();
            probarColision();
            puntuacion1();
            puntuacion2();
        }));
        animacion.setCycleCount(Timeline.INDEFINITE);
        animacion.play();
    }

    //Mover pelota
    private void moverPelota() {
        pelota.setTranslateX(pelota.getTranslateX() + desplazamientoX);
        pelota.setTranslateY(pelota.getTranslateY() - desplazamientoY);
    }


    //COLISIONES
    private void probarColision() {
        if (pelota.getBoundsInParent().intersects(raquetaIzquierda.getBoundsInParent())) {
            desplazamientoX = -desplazamientoX;
        }
        if (pelota.getBoundsInParent().intersects(raquetaDerecha.getBoundsInParent())) {
            desplazamientoX = -desplazamientoX;
        }
        if (pelota.getBoundsInParent().intersects(paredSuperior.getBoundsInParent())) {
            desplazamientoY = -desplazamientoY;
        }
        if (pelota.getBoundsInParent().intersects(paredInferior.getBoundsInParent())) {
            desplazamientoY = -desplazamientoY;
        }
    }



    //Movimiento raquetas izquierdo teclado
    private void movRaqIzq() {
        if (this.teclado.isPressed()) {
            this.raquetaIzquierda.setTranslateY(this.raquetaIzquierda.getTranslateY() - 5);
        }
        if (this.teclado.isPressed()) {
            this.raquetaIzquierda.setTranslateY(this.raquetaIzquierda.getTranslateY() + 5);
        }
    }



    //Puntuaciones
    private void puntuacion1() {
        if (pelota.getTranslateX() <= -this.getWidth() / 2.2 - pelota.getRadius()) {
            puntuacion1.setText(String.valueOf(Integer.parseInt(puntuacion1.getText()) + 1));
            pelota.setTranslateX(0);
            pelota.setTranslateY(0);
            desplazamientoX = -desplazamientoX;
            desplazamientoY = -desplazamientoY;
        }
    }

    private void puntuacion2() {
        if (pelota.getTranslateX() >= this.getWidth() / 2.2 + pelota.getRadius()) {
            puntuacion2.setText(String.valueOf(Integer.parseInt(puntuacion2.getText()) + 1));
            pelota.setTranslateX(0);
            pelota.setTranslateY(0);
            desplazamientoX = -desplazamientoX;
            desplazamientoY = -desplazamientoY;
        }
    }


    // Control de Teclas
    public void moverTecla(KeyEvent event) {
        switch (event.getCode()) {
            case Q:
                this.raquetaIzquierda.setTranslateY(this.raquetaIzquierda.getTranslateY() - 10);
                break;
            case A:
                this.raquetaIzquierda.setTranslateY(this.raquetaIzquierda.getTranslateY() + 10);
                break;
            case P:
                this.raquetaDerecha.setTranslateY(this.raquetaDerecha.getTranslateY() - 10);
                break;
            case L:
                this.raquetaDerecha.setTranslateY(this.raquetaDerecha.getTranslateY() + 10);
                break;
        }

    }
}







