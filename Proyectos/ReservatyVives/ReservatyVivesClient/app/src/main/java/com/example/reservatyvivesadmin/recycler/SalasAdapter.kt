package com.example.reservatyvivesadmin.recycler

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.reservatyvivesadmin.R
import com.example.reservatyvivesadmin.Sala
import com.example.reservatyvivesadmin.databinding.ItemSalaBinding

class SalasAdapter(private var list: MutableList<Sala>, private val clickListenerInterface: ClickListenerInterfaceSala)
    : RecyclerView.Adapter<SalasAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context //inicializar el contexto
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_sala, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)

        holder.setListener(item)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun add(p: Sala) {
        if (!list.contains(p)) {
            list.add(p)
            notifyItemInserted(list.size - 1)
        } else {
            update(p)
        }
    }

    fun update(p: Sala) {
        val index = list.indexOf(p)
        if (index != -1) {
            list.set(index, p)
            notifyItemChanged(index)
        }
    }

    fun delete(p: Sala) {
        val index = list.indexOf(p)
        if (index != -1) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun setData(l: ArrayList<Sala>) {
        this.list = l
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSalaBinding.bind(view)

        fun bind(p: Sala) {
            // binding.imageViewSala
            binding.textViewEdificio.text = p.edificio
            binding.textViewNombre.text = p.nombre
            binding.textViewLocalizacion.text = p.localizacion


        }

        fun setListener(item: Sala) {

        }
    }
}