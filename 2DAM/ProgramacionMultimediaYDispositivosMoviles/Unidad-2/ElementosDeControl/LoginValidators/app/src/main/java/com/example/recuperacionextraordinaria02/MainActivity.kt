package com.example.recuperacionextraordinaria02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recuperacionextraordinaria02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.buttonLogin.setOnClickListener {
            if (mBinding.ediUsername.text.toString() == "user" && mBinding.ediPassword.text.toString() == "password"){
                Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Formulario::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show()

            }
        }
    }
}