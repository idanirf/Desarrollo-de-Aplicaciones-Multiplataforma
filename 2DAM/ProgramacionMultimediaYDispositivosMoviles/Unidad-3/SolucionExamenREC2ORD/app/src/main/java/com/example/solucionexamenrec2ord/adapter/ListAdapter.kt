package com.example.solucionexamenrec2ord.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.solucionexamenrec2ord.R
import com.example.solucionexamenrec2ord.databinding.ItemProfesorBinding
import com.example.solucionexamenrec2ord.entity.Profesor

class ListAdapter(private val profesorList: MutableList<Profesor>) :
RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemProfesorBinding.bind(view)

        fun bind(profesor: Profesor){
            binding.textViewProfesor.text = profesor.nombre
            binding.textViewEmail.text = profesor.email
            binding.textViewAnio.text = profesor.antiguedad.toString()
            binding.textViewTurno.text = profesor.turno.toString()

            if(binding.textViewTurno.text.toString() == "Mañana"){
                binding.card.setBackgroundResource(R.color.color_dia)
            }
            else{
                binding.card.setBackgroundResource(R.color.color_tarde)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_profesor, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profesorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = profesorList[position]
        holder.bind(item)
    }

    fun add(profesor: Profesor) {
        profesorList.add(profesor)
        notifyItemInserted(profesorList.size - 1)
    }
}