package com.example.recextraordinaria11.iu

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recextraordinaria11.data.NoteApplication
import com.example.recextraordinaria11.R
import com.example.recextraordinaria11.adapter.NoteAdapter
import com.example.recextraordinaria11.adapter.NoteOnClickListener
import com.example.recextraordinaria11.databinding.FragmentHomeBinding
import com.example.recextraordinaria11.model.Note
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), NoteOnClickListener {
    lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: NoteAdapter
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var listNote: ArrayList<Note>
    private lateinit var categorySelected: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        setUpButtom()

    }

    private fun setUpButtom() {
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    private fun setUpRecycler(){
        mAdapter = NoteAdapter(mutableListOf(), this)
        mLayout = LinearLayoutManager(requireContext())
        getListNote()
        binding.recyclerHome.apply {
            adapter = mAdapter
        }
    }
    private fun getListNote() {
        lifecycleScope.launch {
            val listNote = NoteApplication.database.notesDao().getAllNote()
            mAdapter.setNotes(listNote)
        }
    }

    override fun onClickEdit(note: Note) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(note)
        Navigation.findNavController(requireView()).navigate(action)

    }

    override fun onClickDelete(note: Note){
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setIcon(R.drawable.baseline_delete_24)
            .setMessage("Atención vas a borrar")
            .setPositiveButton("Confirmar", DialogInterface.OnClickListener { dialogInterface, i ->
                lifecycleScope.launch {
                    NoteApplication.database.notesDao().deleteNote(note)
                }
                mAdapter.remove(note)
                mAdapter.notifyDataSetChanged()
                Toast.makeText(context, "removido", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            })
        val dialogo = builder.create()
        dialogo.show()
    }
}