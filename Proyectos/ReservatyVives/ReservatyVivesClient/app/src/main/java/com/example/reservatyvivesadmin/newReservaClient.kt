package com.example.reservatyvivesadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.reservatyvivesadmin.databinding.FragmentCreateReserbationBinding
import com.example.reservatyvivesadmin.databinding.FragmentNewReservaClientBinding
import com.google.firebase.firestore.FirebaseFirestore


class newReservaClient : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = FragmentNewReservaClientBinding.inflate(inflater, container, false)

        binding.buttonCancelarReserva.setOnClickListener {
            view?.let {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_createReserbationFragment_to_loggingFragment)
            }
        }
        binding.buttonGuardarReserva.setOnClickListener {

            println("creamos reserva ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            //crear sala

            var s = Reserva(
                nombreSala = binding.editTextNombreSala.text.toString(),
                fecha = binding.editTextFechaReserva.text.toString(),
                hora = binding.editTextHoraReserva.text.toString(),
            )

            println( "reserva creada¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            println(s)
            saveReserva(s)

            view?.let {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_newReservaClient_to_loggingFragment)
            }
        }
        return binding.root
    }

    private fun saveReserva(s: Reserva) {
        println( "¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
        println(s)

        val db = FirebaseFirestore.getInstance()
        println( db.collection("reservas").document().toString())

        db.collection("Reservas")
            .add(s)
            .addOnSuccessListener {
                Toast.makeText(activity, "Reserva añadida", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(activity, "Error al insertar reserva", Toast.LENGTH_SHORT).show()
            }


    }
}