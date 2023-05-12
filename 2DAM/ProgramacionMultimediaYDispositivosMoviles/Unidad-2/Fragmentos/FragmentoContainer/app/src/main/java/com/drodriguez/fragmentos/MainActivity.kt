package com.drodriguez.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drodriguez.fragmentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            btnFragA.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView4, PrimerFragmento())
                    .commit()
            }
            btnFragB.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView4, SegundoFragmento())
                    .commit()
            }
        }
    }
}