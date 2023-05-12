package com.example.operaciones;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    //Variables del programa
    TextView resultado;
    EditText numero1, numero2;
    Button botonSuma, botonResta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Captura los valores
        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        botonSuma = findViewById(R.id.buttonSuma);
        botonResta = findViewById(R.id.buttonResta);
        resultado = findViewById(R.id.resultado);

        botonSuma.setOnClickListener(view -> {
            String valor1 = numero1.getText().toString();
            String valor2 = numero2.getText().toString();

            //Conversión de String a -> Int
            int num1 = Integer.parseInt(valor1);
            int num2 = Integer.parseInt(valor2);

            //Realizamos operación de suma
            int suma = num1 + num2;
            //Pasar de Int a -> String el resultado
            String resultadoSuma = String.valueOf(suma);
            //Imprimir resultado por pantalla móvil
            resultado.setText(resultadoSuma);
        });
    }
}
