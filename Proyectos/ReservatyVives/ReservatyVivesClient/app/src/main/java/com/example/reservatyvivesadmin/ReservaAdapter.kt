package com.example.reservatyvivesadmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reservatyvivesadmin.databinding.ItemReserbaBinding

class ReservaAdapter( private val reservaList: MutableList<Reserva>)
    : RecyclerView.Adapter<ReservaAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context //inicializar el contexto
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_reserba, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = reservaList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }

    fun add(reserva: Reserva){
        if (!reservaList.contains(reserva)){
            reservaList.add(reserva)
            notifyItemInserted(reservaList.size-1)
        }else{
            update(reserva, reservaList.indexOf(reserva))
        }
    }

    fun update(reserva: Reserva, indexOf: Int){
        val index = reservaList.indexOf(reserva)
        if (index != -1){
            reservaList.set(index, reserva)
            notifyItemChanged(index)
        }
    }

    fun delete(reserva: Reserva){
        val index = reservaList.indexOf(reserva)
        if (index != -1){
            reservaList.removeAt(index)
            notifyItemRemoved(index)
        }
    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemReserbaBinding.bind(view)

        fun bind(reserva: Reserva){
            binding.textViewNombreSala.text = reserva.nombreSala
            binding.textViewFecha.text = reserva.fecha
            binding.textViewNombreUsuario.text = reserva.user
        }

    }
}