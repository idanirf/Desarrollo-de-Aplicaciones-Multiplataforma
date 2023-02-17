package com.example.reservatyvivesadmin

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservatyvivesadmin.databinding.FragmentRecyclerSalasBinding
import com.example.reservatyvivesadmin.recycler.ClickListenerInterfaceSala
import com.example.reservatyvivesadmin.recycler.SalasAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class RecyclerSalasFragment : Fragment(), ClickListenerInterfaceSala {

    private lateinit var binding: FragmentRecyclerSalasBinding
    private lateinit var mAdapter: SalasAdapter

    private lateinit var mListenerRegistration: ListenerRegistration
    private  var mlista = ArrayList<Sala>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerSalasBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonVolverREserbasRecicler.setOnClickListener {
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
        mAdapter = SalasAdapter(mutableListOf(), this)
        binding.recyclerSalas.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter

        }
    }


    private fun getAllSalas() {
        val db = FirebaseFirestore.getInstance()

        db.collection("Salass")
            .get()
            .addOnSuccessListener { snapshots ->
                for (document in snapshots){
                    val sala = document.toObject(Sala::class.java)
                    mAdapter.add(sala)
                }

            }
            .addOnFailureListener{
                Toast.makeText(this.context, "Error al consultar los datos", Toast.LENGTH_SHORT).show()
            }
    }

    override fun click(s: Sala): Boolean {
        TODO("Not yet implemented")
    }


}