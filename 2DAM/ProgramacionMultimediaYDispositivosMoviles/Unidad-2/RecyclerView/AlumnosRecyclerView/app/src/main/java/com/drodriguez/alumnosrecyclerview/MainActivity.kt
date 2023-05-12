package com.drodriguez.alumnosrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.drodriguez.alumnosrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var alumnoAdapter: AlumnoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflamos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creamos una lista de alumnos
        val data = ArrayList<Alumno>()
        //Agregamos alumnos a la lista de forma aleatoria de 1 a 30
        for (i in 1..30) {data.add(Alumno())}

        //Creamos el adapter
        alumnoAdapter = AlumnoAdapter(data){alumno -> verDataAlum(alumno)}

        // Mostramos en pantalla el resultado del recyclerView
        binding.recycler.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = alumnoAdapter
        }
    }

    //Funcion para mostrar los datos del alumno en un dialogo al pulsar sobre un alumno
    private fun verDataAlum(note: Alumno) {
        val builder = AlertDialog.Builder(this)
        .setTitle("Datos del alumno")
            .setPositiveButton("Okey",
                {dialogInterface, i-> alumnoAdapter.okeyBoton(note)})
            .setMessage("Nombre: ${note.nombre} ${note.apellidoUno} ${note.apellidoDos}  \n Edad: ${note.edad} " +
                    " \n Nota media: ${note.notaMedia}")
        builder.create().show()

    }
}