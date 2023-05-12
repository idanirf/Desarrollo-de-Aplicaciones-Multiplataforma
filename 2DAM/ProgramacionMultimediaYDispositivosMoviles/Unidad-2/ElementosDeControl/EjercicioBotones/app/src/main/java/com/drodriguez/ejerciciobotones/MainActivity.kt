package com.drodriguez.ejerciciobotones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var radioGroupSaludos = findViewById<RadioGroup>(R.id.radioGroupSaludos)
        var radioButtonHola = findViewById<RadioButton>(R.id.radioButtonHola)
        var radioButtonAdios = findViewById<RadioButton>(R.id.radioButtonAdios)

    }
}