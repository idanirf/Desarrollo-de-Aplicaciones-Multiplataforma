package com.example.recextraordinaria11.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recextraordinaria11.R
import com.example.recextraordinaria11.databinding.ItemNoteBinding
import com.example.recextraordinaria11.model.Note

class NoteAdapter(private var noteList: MutableList<Note>,
                  var listener: NoteOnClickListener
): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = noteList.get(position)
        holder.bind(item)
        holder.onListener(item)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun add(note: Note) {
        if (!noteList.contains(note)){
            noteList.add(note)
            notifyItemInserted(noteList.size-1)
        }
    }

    fun update(note: Note) {
        val index = noteList.indexOf(note)
        if (index!= -1) {
            noteList.set(index, note)
            notifyItemChanged(noteList.size-1)
        }
    }

    fun remove(note: Note) {
        noteList.remove(note)
        notifyDataSetChanged()
    }

    fun setNotes(list: MutableList<Note>) {
        this.noteList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Esto viene del nombre del xml name
        private val binding = ItemNoteBinding.bind(view)

        fun bind(note: Note) {
            binding.textViewTitulo.text = note.title
            binding.textViewSubtitulo.text = note.subTitle
            binding.textViewCategory.text = note.category
            if (note.tick){
                binding.layoutItem.setBackgroundColor(Color.parseColor("#F05454"))
            } else {
                binding.layoutItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            Glide.with(context)
                .load(note.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPhoto)
        }
        fun onListener(note: Note) {
            binding.imageView.setOnClickListener {
                listener.onClickEdit(note)
            }
            binding.imageView2.setOnClickListener {
                listener.onClickDelete(note)
            }
        }
    }
}
