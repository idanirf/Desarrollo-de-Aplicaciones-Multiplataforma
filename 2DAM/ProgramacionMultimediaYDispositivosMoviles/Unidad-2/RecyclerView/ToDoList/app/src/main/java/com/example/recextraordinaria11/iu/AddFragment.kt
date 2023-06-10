package com.example.recextraordinaria11.iu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recextraordinaria11.data.NoteApplication
import com.example.recextraordinaria11.R
import com.example.recextraordinaria11.databinding.FragmentAddBinding
import com.example.recextraordinaria11.model.Note
import kotlinx.coroutines.launch


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    var categorySelected: String =""
    var tickNote = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextURL.addTextChangedListener {
            Glide.with(this)
                .load(binding.editTextURL.text.toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
               .into(binding.imagenAdd)
        }
        llenarDatos()
        binding.floatingActionButton2.setOnClickListener {
            createNotes()
        }
    }

    private fun llenarDatos() {
        val opcionCategory = resources.getStringArray(R.array.category)
        val spinner = binding.spinnerCategory

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                categorySelected = opcionCategory[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.switchPrioridad.setOnCheckedChangeListener { compoundButton, isChecked ->
            tickNote = isChecked
        }    }

    private fun createNotes() {
        val note = Note(
            title = binding.editTextText.text.toString(),
            subTitle = binding.editTextSubtitulo.text.toString(),
            descripcion = binding.editTextDesc.text.toString(),
            category = categorySelected,
            tick = tickNote,
            photoUrl = binding.editTextURL.text.toString()
        )


        //Corrutina para añadir un elemento a la BD
        lifecycleScope.launch {
            NoteApplication.database.notesDao().addNote(note)
        }
        Toast.makeText(requireContext(), "añadido", Toast.LENGTH_SHORT).show()
        Log.i("E", "estoy en dialog")

        //volver al fragmet Homefragment
        Navigation.findNavController(requireView()).navigate(R.id.action_addFragment_to_homeFragment)

    }
}