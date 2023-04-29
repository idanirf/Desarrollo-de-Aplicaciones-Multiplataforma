package com.example.recuperacionextraordinaria03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recuperacionextraordinaria03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = DatoAdapter(lista)
    }
}
private val lista = (1..100).map { Dato("Palabra $it","https://loremflickr.com/320/240/city/?lock=$it") }
