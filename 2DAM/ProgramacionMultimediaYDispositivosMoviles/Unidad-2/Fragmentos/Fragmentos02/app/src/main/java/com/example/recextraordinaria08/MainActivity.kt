package com.example.recextraordinaria08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recextraordinaria08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var fragment: PrimerFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.bottomnav.setOnItemReselectedListener{
            when(it.itemId){
                R.id.action_page1->{
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.hostFragment, PrimerFragment())
                    fragmentTransaction.commit()
                    Toast.makeText(this, "Página 1", Toast.LENGTH_SHORT).show()
                    true
                }
            R.id.action_page2 ->{
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.hostFragment, SegundoFragment())
                fragmentTransaction.commit()
                Toast.makeText(this, "Página 2", Toast.LENGTH_SHORT).show()
            true
        }
            else -> false
        }
        }
    }
}