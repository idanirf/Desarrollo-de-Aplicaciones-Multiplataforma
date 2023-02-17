package com.example.reservatyvivesadmin

import android.content.ClipData.Item
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.reservatyvivesadmin.databinding.FragmentLoggingBinding


class LoggingFragment : Fragment() , MenuProvider{

    private  lateinit var binding: FragmentLoggingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("creado !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("inflade !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        binding = FragmentLoggingBinding.inflate(layoutInflater)



        binding.buttonSalas.setOnClickListener {
            view?.let { Navigation.findNavController(binding.root).navigate(R.id.action_loggingFragment_to_recyclerSalasFragment)}
        }

        binding.aAdirReserva.setOnClickListener {
            view?.let { Navigation.findNavController(binding.root).navigate(R.id.action_loggingFragment_to_newReservaClient)}
        }

        return binding.root
    }

    //crear menu en un fragment
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        println("entra en el menú¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
        if(menuItem.itemId == R.id.salirItem){
            println("salir¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
        }
        if(menuItem.itemId == R.id.salas_itenMenu){
            println("salas111¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            view?.let { Navigation.findNavController(it.rootView).navigate(R.id.action_loggingFragment_to_recyclerSalasFragment) }
        }
        if(menuItem.itemId == R.id.gestionReserbasItemMenu){
            println("gestionreserbas¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            view?.let { Navigation.findNavController(it.rootView).navigate(R.id.action_loggingFragment_to_recyclerReserbasUsuariosFragment) }
        }
        if(menuItem.itemId == R.id.sesionIntemMenu){
            println("sesion¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
        }
        if(menuItem.itemId == R.id.crearSalaItenMenu){
            println("crear sala¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡")
            view?.let { Navigation.findNavController(it.rootView).navigate(R.id.action_loggingFragment_to_createReserbationFragment)}
        }
        return true
    }




}