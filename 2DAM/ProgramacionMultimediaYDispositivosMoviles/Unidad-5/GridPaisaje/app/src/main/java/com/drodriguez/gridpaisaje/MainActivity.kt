package com.drodriguez.gridpaisaje

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var paisajeAdapter: PaisajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vamos a cargar los datos
       val paisajeList = DataSources().loadDatos()


        //crear el adaptador y controlar cuando se pulsa
        paisajeAdapter = PaisajeAdapter(paisajeList){ elementoData ->

            //crear el intent
            val intent = Intent(this, SegundaActivity::class.java)
            //pasar los datos a la segunda activity
            intent.putExtra("IMAGEN", elementoData )
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler)

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = paisajeAdapter
        }
    }
}