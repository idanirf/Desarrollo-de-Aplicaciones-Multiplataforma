package com.example.recuperacionextraordinaria02

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.recuperacionextraordinaria02.databinding.ActivityFormularioBinding
import com.example.recuperacionextraordinaria02.databinding.ActivityMainBinding

class Formulario : AppCompatActivity() {
    private lateinit var mBinding: ActivityFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        onClickRadioButton()

        onSetupSpinner()


    }

    private fun onSetupSpinner(){
        val opcionDepartamento = resources.getStringArray(R.array.familias)
        val spinner = mBinding.spinner
        ArrayAdapter.createFromResource(this,R.array.familias, android.R.layout.simple_spinner_item).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, p3: Long
            ) {
                Toast.makeText(this@Formulario, opcionDepartamento[pos], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun onClickRadioButton(){
        mBinding.radio.setOnCheckedChangeListener{radio, checkedId ->
            when(checkedId){
                R.id.radioProfesor ->{
                    Toast.makeText(this,mBinding.radioProfesor.text.toString(),Toast.LENGTH_SHORT).show()
                }
                R.id.radioAlumno ->{
                    Toast.makeText(this,mBinding.radioAlumno.text.toString(),Toast.LENGTH_SHORT).show()
                }
                R.id.radioInvitado ->{
                    Toast.makeText(this,mBinding.radioInvitado.text.toString(),Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}