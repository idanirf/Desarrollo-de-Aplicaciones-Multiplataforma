package com.example.recuperacionextraordinaria03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recuperacionextraordinaria03.databinding.ItemPalabraBinding

class DatoAdapter(private var listPalabra:List<Dato>): RecyclerView.Adapter<DatoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_palabra, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPalabra.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dato = listPalabra[position]
        holder.bind(dato)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemPalabraBinding.bind(view)

        fun bind(data: Dato) {
            binding.itemPalabra.text = data.palabra
        }

    }
}