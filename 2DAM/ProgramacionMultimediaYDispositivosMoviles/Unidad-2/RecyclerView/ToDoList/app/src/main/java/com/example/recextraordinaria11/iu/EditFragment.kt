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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recextraordinaria11.data.NoteApplication
import com.example.recextraordinaria11.R
import com.example.recextraordinaria11.databinding.FragmentEditBinding
import com.example.recextraordinaria11.model.Note
import kotlinx.coroutines.launch


class EditFragment : Fragment() {
    lateinit var binding : FragmentEditBinding

    val oldNote by navArgs<EditFragmentArgs>()  //recoger los datos enviados por navargs

    private lateinit var categorySelected: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //mostrar los datos en el fragment
        binding.editTextText.setText(oldNote.date.title)
        binding.editTextSubtitulo.setText(oldNote.date.subTitle)
        binding.editTextDesc.setText(oldNote.date.descripcion)

        binding.editTextURL.setText(oldNote.date.photoUrl)

        //mostrar la imagen en la pantalla
        Glide.with(this)
            .load(oldNote.date.photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imagenAdd)

        //trabajar con tick
        if(oldNote.date.tick){
            binding.switchPrioridad.isChecked = true
        }else {
            binding.switchPrioridad.isChecked = false
        }
        //trabajar con la lista
        val opcionCategory = resources.getStringArray(R.array.category) //cargar la lista

        //crear el adaptador
        ArrayAdapter.createFromResource(requireContext(),R.array.category, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCategory.adapter = it
        }

        //mostrar el elemento que esta en la bd
        categorySelected = binding.spinnerCategory.setSelection(opcionCategory.indexOf(oldNote.date.category)).toString()

        //cuando se selecciona el spinner
        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                categorySelected = opcionCategory[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //llamar a la funcion update
        binding.floatingActionButton2.setOnClickListener {
            updateNote(it)
        }
    }

    private fun updateNote(it: View?) {
        //crear el objeto Note
        val note = Note(
            oldNote.date.id,
            title = binding.editTextText.text.toString(),
            subTitle = binding.editTextSubtitulo.text.toString(),
            descripcion = binding.editTextDesc.text.toString(),
            category = categorySelected,
            tick = binding.switchPrioridad.isChecked,
            photoUrl = binding.editTextURL.text.toString()
        )


        //Corrutina para añadir un elemento a la BD
        lifecycleScope.launch {
            NoteApplication.database.notesDao().updateNote(note)
        }
        Toast.makeText(requireContext(), "modificado", Toast.LENGTH_SHORT).show()
        Log.i("E", "estoy en dialog")

        //volver al fragmet Homefragment
        Navigation.findNavController(requireView()).navigate(R.id.action_editFragment_to_homeFragment)
    }
}

