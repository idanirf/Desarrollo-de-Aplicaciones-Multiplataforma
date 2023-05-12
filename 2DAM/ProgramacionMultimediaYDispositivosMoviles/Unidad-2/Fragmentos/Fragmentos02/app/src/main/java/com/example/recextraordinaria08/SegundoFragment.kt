package com.example.recextraordinaria08

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recextraordinaria08.databinding.FragmentPrimerBinding
import com.example.recextraordinaria08.databinding.FragmentSegundoBinding


class SegundoFragment : Fragment() {
    private var _binding: FragmentSegundoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSegundoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}