package com.example.botoncontador;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
/*
 * @author Daniel Rodriguez Fernández
 * 2DAM
 * IES LUIS VIVES
 * @date 26/09/2022
 */
public class MainActivity extends AppCompatActivity {
    TextView resultadoContador;
    Button botonSuma, botonResta, botonReset, botonSalida;

    Integer contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultadoContador = findViewById(R.id.resultadoContador);
        botonSuma = findViewById(R.id.botonSuma);
        botonResta = findViewById(R.id.botonResta);
        botonReset = findViewById(R.id.botonReset);
        botonSalida = findViewById(R.id.botonSalida);

        /*
         * Botón de salida de la aplicación
         */
        botonSalida.setOnClickListener(view -> {
                    finish();
                }
        );

        /*
         * Botón de resta de la aplicación
         */
        botonResta.setOnClickListener(view -> {
                    contador--;
                    contador();
                }
        );

        /*
         * Botón de reset de la aplicación, pone el contador a 0
         */
        botonReset.setOnClickListener(view -> {
                    resultadoContador.setText("0");
                }
        );

        /*
         * Botón de suma de la aplicación
         */
        botonSuma.setOnClickListener(view -> {
                    contador++;
                    contador();
                }
        );
    }

    /*
     * Función que muestra el contador en pantalla actualizado
     */
    private void contador() {
        resultadoContador.setText(String.valueOf(contador));
    }
}