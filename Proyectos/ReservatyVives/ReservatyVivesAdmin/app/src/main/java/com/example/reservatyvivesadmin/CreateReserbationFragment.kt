package com.example.reservatyvivesadmin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.reservatyvivesadmin.databinding.FragmentCreateReserbationBinding
import com.google.firebase.firestore.FirebaseFirestore


class CreateReserbationFragment : Fragment() {

 private lateinit var binding: FragmentCreateReserbationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = FragmentCreateReserbationBinding.inflate(inflater, container, false)

        binding.buttonCancelarSala.setOnClickListener {
            view?.let {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_createReserbationFragment_to_loggingFragment)
            }
        }
        binding.buttonAAdirImagen.setOnClickListener {
            println("disteClic en el boton")
            //abrir la galeria
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultLauncher.launch(intent)
        }
        binding.buttonGuargarSala.setOnClickListener {

            println("creamos sala ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            //crear sala

            var s = Sala(
                nombre = binding.editTextTextNombreSala.text.toString(),
                localizacion = binding.editTextTextLocalizacionSala.text.toString(),
                edificio = binding.editTextTextEdificioSala.text.toString(),
                horaApertura = binding.editTextNumberApertura.text.toString().toInt(),
                horaCierre = binding.editTextNumberCierre.text.toString().toInt(),
                imagen = ""
            )

            println( "sala creada¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            println(s)
            saveSala(s)

            view?.let {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_createReserbationFragment_to_loggingFragment)
            }
        }
        return binding.root
    }

    //para trabajar las imagenes
    private var photoSelectUri : Uri? = null

    //combprobar el resultado del Intent para la imagen
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            photoSelectUri = it.data?.data
        }
    }

    private fun saveSala(s: Sala) {
        println( "¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
        println(s)

        val db = FirebaseFirestore.getInstance()
        println( db.collection("salas").document().toString())

        db.collection("Salass")
            .add(s)
            .addOnSuccessListener {
                Toast.makeText(activity, "Sala añadida", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(activity, "Error al insertar sala", Toast.LENGTH_SHORT).show()
            }


    }
}