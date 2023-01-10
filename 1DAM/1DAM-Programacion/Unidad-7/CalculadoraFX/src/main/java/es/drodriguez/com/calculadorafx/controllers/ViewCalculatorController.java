package es.drodriguez.com.calculadorafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class ViewCalculatorController {
    private int operadorA, operadorB, resultado;

    @FXML
    private TextField operador1;

    @FXML
    private TextField operador2;

    @FXML
    private TextField areaResultado;

    @FXML
    Button botonLimpiar;

    private void cleanCampos(){
        operador1.setText("0");
        operador2.setText("0");
        areaResultado.setText("0");
    }

    @FXML
    private void inicialize(){
        System.out.println("Inicializando...");
        botonLimpiar.setOnAction(event -> {
            System.out.println(event.getSource());
            cleanCampos();
        });
        cleanCampos();
    }

    //Acción que cierra desde el menú superior la ventana
    @FXML
    private void onMenuCerrarAction(ActionEvent actionEvent){
        System.out.println(actionEvent.getSource());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir de la calculadora");
        alert.setHeaderText("¿Seguro que quieres salir?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        }
    }

    @FXML
    private void onBotonSumaAction(ActionEvent actionEvent){
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos()){
            resultado = operadorA + operadorB;
            areaResultado.setText(String.valueOf(resultado));
        } else {
            messageError("Error");
        }
    }

    @FXML
    private void onBotonRestaAction(ActionEvent actionEvent){
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos()){
            resultado = operadorA - operadorB;
            areaResultado.setText(String.valueOf(resultado));
        } else {
            messageError("Error");
        }
    }

    @FXML
    private void onMultiplicacionAction(ActionEvent actionEvent){
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos()){
            resultado = operadorA * operadorB;
            areaResultado.setText(String.valueOf(resultado));
        } else {
            messageError("Error");
        }
    }

    @FXML
    private void onBotonDivisionAction(ActionEvent actionEvent){
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getSource());
        if (comprobarCampos() && operadorB != 0){
            resultado = operadorA / operadorB;
            areaResultado.setText(String.valueOf(resultado));

        } else {
            messageError("Err");
        }
    }

    private boolean comprobarCampos(){
        boolean resultado = !operador1.getText().isEmpty();
        if (operador2.getText().isEmpty()){
            resultado = false;
        }
        try {
            operadorA = Integer.parseInt(operador1.getText());
            operadorB = Integer.parseInt(operador2.getText());
        } catch (NumberFormatException e){
            resultado = false;
        }
        return resultado;
    }

    private void messageError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nuevo Error");
        alert.setHeaderText("Error en los campos de entrada");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
