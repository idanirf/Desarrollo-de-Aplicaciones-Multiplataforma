package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ViewNotaBinding

class NotaAdapter(private val notaList: MutableList<Nota>,
                  private val listener: (Nota)->Unit) : RecyclerView.Adapter<NotaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notaList.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount(): Int {
        return notaList.size
    }

    fun add(nota: Nota) {
        notaList.add(nota)
        notifyDataSetChanged()
    }

    fun remove(nota: Nota){
        notaList.remove(nota)
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewNotaBinding.bind(view)

        fun bind(nota: Nota) {
            binding.tvDescription.text = nota.descripcion
            binding.cbFinished.isChecked = nota.isFinished
        }

    }

}