package com.example.recextraordinaria11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.recextraordinaria11.databinding.ActivityMainBinding
import com.example.recextraordinaria11.iu.CalendarFragment
import com.example.recextraordinaria11.iu.CategoryFragment
import com.example.recextraordinaria11.iu.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Controlador del navegador
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val nacvController = navHostFragment.navController
        setupActionBarWithNavController(nacvController)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, HomeFragment())
                    fragmentTransaction.commit()
                    true
                }

                R.id.actionCategories -> {
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, CategoryFragment())
                    fragmentTransaction.commit()
                    true
                }

                R.id.actionCalendar -> {
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, CalendarFragment())
                    fragmentTransaction.commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment_container)
        return navController.navigateUp()
    }
}