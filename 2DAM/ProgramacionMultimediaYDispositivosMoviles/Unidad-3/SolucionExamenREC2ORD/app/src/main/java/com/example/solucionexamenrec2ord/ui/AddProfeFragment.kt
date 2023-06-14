package com.example.solucionexamenrec2ord.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.solucionexamenrec2ord.R
import com.example.solucionexamenrec2ord.databinding.FragmentAddProfeBinding
import com.example.solucionexamenrec2ord.entity.Profesor
import com.google.firebase.firestore.FirebaseFirestore


class AddProfeFragment : Fragment() {
    private lateinit var mBinding: FragmentAddProfeBinding
    private lateinit var mAdapter: ListAdapter
    lateinit var profesorValor: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAddProfeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configButton()
    }

    private fun configButton() {
        mBinding.floatingActionButton.setOnClickListener {
            val profesor = Profesor(
                nombre = mBinding.edittextName.text.toString().trim(),
                email = mBinding.edittextEmail.text.toString().trim(),
                turno = when (mBinding.radioGroup.checkedRadioButtonId) {
                    R.id.radioButtonMañana -> "Mañana"
                    R.id.radioButtonTarde -> "Tarde"
                    else -> ""
                },
                antiguedad = mBinding.editTextIngreso.text.toString().trim().toInt()
            )
            grabarDatos(profesor)
            mBinding.edittextName.setText("")
            mBinding.edittextEmail.setText("")
            mBinding.editTextIngreso.setText("")
            mBinding.radioGroup.clearCheck()

            val navController = findNavController()
            navController.navigate(R.id.action_home)
        }
    }

    private fun grabarDatos(profesor: Profesor) {
        val mFirebaseFirestore = FirebaseFirestore.getInstance()

        mFirebaseFirestore.collection("profesor")

            .add(profesor)
            .addOnSuccessListener {
                Toast.makeText(requireActivity(), "Completado, se ha añadido", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error no añadido", Toast.LENGTH_SHORT).show()
            }
    }

}