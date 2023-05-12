package com.example.aplicacionvida;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Estado", "On Create");
    }

    //
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Estado", "On Resume");
    }

    // La actividad ya no está en primer plano
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Estado", "On Pause");
    }

    // La actividad está terminando
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Estado", "On Destroy");
    }

    // El usuario puede ver la actividad
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Estado", "On Start");
    }

    // El usuario ya no puede ver tu actividad
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Estado", "On Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Estado", "On Restart");
    }
}