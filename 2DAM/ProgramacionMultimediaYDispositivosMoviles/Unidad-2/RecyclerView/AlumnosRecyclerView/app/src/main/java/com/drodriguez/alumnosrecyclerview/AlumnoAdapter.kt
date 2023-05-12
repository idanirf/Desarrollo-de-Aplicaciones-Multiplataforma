package com.drodriguez.alumnosrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlumnoAdapter(private val list: MutableList<Alumno>,
private val listener: (Alumno) -> Unit): RecyclerView.Adapter<AlumnoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_alumno, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {listener(item)}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun okeyBoton(note: Alumno){
        // Fun del botón okey
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var al = view.findViewById<TextView>(R.id.idAlumnView)
        fun bind(alumno: Alumno){
            al.setText(alumno.nombre)
        }
    }



}