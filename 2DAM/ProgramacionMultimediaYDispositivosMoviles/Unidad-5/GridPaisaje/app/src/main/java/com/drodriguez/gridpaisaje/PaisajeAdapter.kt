package com.drodriguez.gridpaisaje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PaisajeAdapter(
    private val paisajeList: List<ElementoData>,
    private val listener: (ElementoData)->Unit )
    :RecyclerView.Adapter<PaisajeAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val imagen : ImageView = view.findViewById(R.id.image_paisaje)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.paisaje_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = paisajeList[position]
        holder.imagen.setImageResource(item.paisajeId)

        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int {
       return paisajeList.size
    }
}