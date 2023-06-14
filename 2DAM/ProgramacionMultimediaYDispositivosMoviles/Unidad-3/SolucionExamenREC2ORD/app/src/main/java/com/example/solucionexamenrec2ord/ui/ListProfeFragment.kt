package com.example.solucionexamenrec2ord.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solucionexamenrec2ord.R
import com.example.solucionexamenrec2ord.adapter.ListAdapter
import com.example.solucionexamenrec2ord.databinding.FragmentListProfeBinding
import com.example.solucionexamenrec2ord.entity.Profesor
import com.google.firebase.firestore.FirebaseFirestore

class ListProfeFragment : Fragment() {
    private lateinit var binding: FragmentListProfeBinding
    private lateinit var mAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListProfeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configButton()
    }


    private fun configButton() {
        binding.imageButtonList.setOnClickListener {
            mostrarTodos()
            configRecycler()
        }
        binding.imageButtonDiurno.setOnClickListener {
            diurno()
            configRecycler()
        }
        binding.imageButtonTarde.setOnClickListener {
            tarde()
            configRecycler()
        }

    }

    private fun tarde() {
        val mFirebaseFirestore = FirebaseFirestore.getInstance()
        mFirebaseFirestore.collection("profesor")
            .whereEqualTo("turno", "Tarde") // Filtrar por turno de tarde
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val profesor = document.toObject(Profesor::class.java)
                    profesor.id = document.id
                    mAdapter.add(profesor)
                    Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireActivity(),
                    "Error al consultar los datos",
                    Toast.LENGTH_SHORT
                ).show()
            }    }

    private fun diurno() {
        val mFirebaseFirestore = FirebaseFirestore.getInstance()
        mFirebaseFirestore.collection("profesor")
            .whereEqualTo("turno", "Mañana") // Filtrar por turno de mañana
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val profesor = document.toObject(Profesor::class.java)
                    profesor.id = document.id
                    mAdapter.add(profesor)
                    Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireActivity(),
                    "Error al consultar los datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun configRecycler() {
        mAdapter = ListAdapter(mutableListOf())
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun mostrarTodos() {
        val mFirebaseFirestore = FirebaseFirestore.getInstance()
        mFirebaseFirestore.collection("profesor")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    val profesor = document.toObject(Profesor::class.java)
                    profesor.id = document.id  //la id del producto es el id de firebase
                    mAdapter.add(profesor)
                    Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireActivity(),
                    "Error al consultar los datos",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
}