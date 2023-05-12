package com.example.calculadora;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    int res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText numeroPrimero = findViewById(R.id.numeroPrimero);
        EditText numeroSegundo = findViewById(R.id.numeroSegundo);
        RadioGroup radioGroupCalculadora = findViewById(R.id.radioGroupCalculadora);

        //Clase anónima para chekear que no están seleccionados los radioGroupCalculadora
        radioGroupCalculadora.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroupCalculadora, int i) {

            }
        });
        TextView textViewResultado = findViewById(R.id.textViewResultado);


        radioGroupCalculadora.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroupCalculadora, int isCheched) {
                int nPrimero = Integer.parseInt(numeroPrimero.getText().toString());
                int nSegundo = Integer.parseInt(numeroSegundo.getText().toString());
                //TextView textViewResultado = findViewById(R.id.textViewResultado);

                RadioButton radioButton = radioGroupCalculadora.findViewById(isCheched);
                switch (radioButton.getId()) {
                    case R.id.radioButtonSuma:
                        res = nPrimero + nSegundo;
                        break;
                    case R.id.radioButtonResta:
                        res = nPrimero - nSegundo;
                        break;
                    case R.id.radioButtonMultiplicacion:
                        res = nPrimero * nSegundo;
                        break;
                    case R.id.radioButtonDiv:
                        res = nPrimero / nSegundo;
                        break;
                }
                textViewResultado.setText("Resultado: " + res);

            }
        });

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroPrimero.setText("0");
                numeroSegundo.setText("0");
                textViewResultado.setText("0");


            }
        });
    }
}