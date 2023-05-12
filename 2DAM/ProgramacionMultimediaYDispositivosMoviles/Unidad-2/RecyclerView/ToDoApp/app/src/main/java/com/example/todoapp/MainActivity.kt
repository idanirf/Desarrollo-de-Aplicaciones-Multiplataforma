package com.example.todoapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notaAdapter: NotaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datos = mutableListOf(
            Nota(1,"Compra"),
            Nota(2,"Deberes")
        )
        notaAdapter = NotaAdapter(datos){nota ->
            Toast.makeText(this,nota.descripcion, Toast.LENGTH_SHORT).show()
            deleteNoteAuto(nota)
        }

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = notaAdapter
        }

        binding.btnAdd.setOnClickListener{
            if (binding.etDescription.text.toString().isNotBlank()){
                val nota = Nota((notaAdapter.itemCount +1).toLong(),
                    binding.etDescription.text.toString().trim())
                addNotaAuto(nota)
                binding.etDescription.text?.clear()
            }
        }
    }

    private fun addNotaAuto(nota: Nota){
        notaAdapter.add(nota)
    }

    private fun deleteNoteAuto(nota: Nota){
        val builder = AlertDialog.Builder(this)
            .setTitle("¿Eliminar registro?")
            .setPositiveButton("eliminar",{dialogInterface, i ->
                notaAdapter.remove(nota)
            })
            .setNegativeButton("cancelar", null)
        builder.create().show()
    }
}