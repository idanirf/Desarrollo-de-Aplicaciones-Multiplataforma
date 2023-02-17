package com.example.reservatyvivesadmin

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservatyvivesadmin.databinding.FragmentRecyclerReserbasUsuariosBinding
import com.example.reservatyvivesadmin.databinding.FragmentRecyclerSalasBinding
import com.example.reservatyvivesadmin.recycler.ClickListenerInterfaceReserva
import com.example.reservatyvivesadmin.recycler.ReserbasAdapter
import com.example.reservatyvivesadmin.recycler.SalasAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration


class RecyclerReserbasUsuariosFragment : Fragment() ,ClickListenerInterfaceReserva {

    private lateinit var binding: FragmentRecyclerReserbasUsuariosBinding
    private lateinit var mAdapter: ReserbasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerReserbasUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonVolverSalasRecicler.setOnClickListener {
            Toast.makeText(context, "pulsaste boton volver", Toast.LENGTH_SHORT).show()
            view?.let {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_recyclerSalasFragment_to_loggingFragment)
            }
        }

        getAllSalas()
        setupRecylerView()
    }

    private fun setupRecylerView() {
        mAdapter = ReserbasAdapter(mutableListOf(), this)
        binding.reciclerReserbas.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter

        }
    }


    private fun getAllSalas() {
        val db = FirebaseFirestore.getInstance()

        db.collection("Reservas")
            .get()
            .addOnSuccessListener { snapshots ->
                for (document in snapshots) {
                    val sala = document.toObject(Reserva::class.java)
                    mAdapter.add(sala)
                }

            }
            .addOnFailureListener {
                Toast.makeText(this.context, "Error al consultar los datos", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    override fun click(s: Reserva): Boolean {
        TODO("Not yet implemented")
    }
}