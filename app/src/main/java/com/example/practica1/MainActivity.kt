package com.example.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_opciones,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun clickCalcular(view: View) {
        val etn_a:EditText = findViewById(R.id.ETN_a)
        val etn_b:EditText = findViewById(R.id.ETN_b)
        val etn_c:EditText = findViewById(R.id.ETN_c)

        if(!etn_isEmpty(etn_a) and !etn_isEmpty(etn_b) and !etn_isEmpty(etn_c)){
            var numero_a = etn_a.text.toString().toInt()
            var numero_b = etn_b.text.toString().toInt()
            var numero_c = etn_c.text.toString().toInt()

        }else{
            Toast.makeText(this@MainActivity,getString(R.string.toast_campo_vacio),Toast.LENGTH_SHORT).show()
            if(etn_isEmpty(etn_a)) etn_a.error = resources.getString(R.string.etn_error_valorRequerido)
            if(etn_isEmpty(etn_b)) etn_b.error = resources.getString(R.string.etn_error_valorRequerido)
            if(etn_isEmpty(etn_c)) etn_c.error = resources.getString(R.string.etn_error_valorRequerido)
        }

    }

    fun etn_isEmpty(et_x:EditText): Boolean {
        if(et_x.text.isEmpty()){
            return true
        }else{
            return false
        }
    }
}