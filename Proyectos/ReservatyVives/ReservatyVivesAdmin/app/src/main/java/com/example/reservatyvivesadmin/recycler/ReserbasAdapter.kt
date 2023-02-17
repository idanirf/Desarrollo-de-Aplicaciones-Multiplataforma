package com.example.reservatyvivesadmin.recycler

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.reservatyvivesadmin.R
import com.example.reservatyvivesadmin.Reserva
import com.example.reservatyvivesadmin.databinding.ItemReserbaBinding

class ReserbasAdapter(private var list: MutableList<Reserva>, private val clickListenerInterfaceReserva: ClickListenerInterfaceReserva)
    : RecyclerView.Adapter<ReserbasAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context //inicializar el contexto
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_reserba, parent, false)

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

    fun add(p: Reserva){
        if (!list.contains(p)){
            list.add(p)
            notifyItemInserted(list.size-1)
        }else{
            update(p)
        }
    }

    fun update(p: Reserva){
        val index = list.indexOf(p)
        if (index != -1){
            list.set(index, p)
            notifyItemChanged(index)
        }
    }

    fun delete(p: Reserva){
        val index = list.indexOf(p)
        if (index != -1){
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun setData(l: ArrayList<Reserva>) {
        this.list = l
        notifyDataSetChanged()
    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemReserbaBinding.bind(view)

        fun bind(p: Reserva) {
            // binding.imageViewSala
            binding.textViewFecha.text = p.fecha
            binding.textViewNombreSala.text = p.nombreSala
            binding.textViewNombreUsuario.text = p.user

            /**

            Glide.with(mContext)
            .load(p.imagen)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imageViewSala)



             */


        }

        fun setListener(item: Reserva) {

        }


    }
    }