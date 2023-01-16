package com.drodriguez.gridpaisaje

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val paisaje = findViewById<ImageView>(R.id.imageView)

        paisaje.setImageResource(intent.getIntExtra("IMAGEN", R.drawable.image_1))
    }
}