package com.example.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var formula = ""
    private var opcion_seleccionada:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this
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

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val tv_a = findViewById<TextView>(R.id.tv_a_title)
        val tv_b = findViewById<TextView>(R.id.tv_b_title)
        val tv_c = findViewById<TextView>(R.id.tv_c_title)
        val imagen = findViewById<ImageView>(R.id.imageView)

        formula = parent.getItemAtPosition(pos).toString()
        when(formula){
            parent.getItemAtPosition(0).toString() -> {
                imagen.setImageResource(R.drawable.dgtic_formula_general)
                tv_a.setText(R.string.tv_a_option)
                tv_b.setText(R.string.tv_b_option)
                tv_c.setText(R.string.tv_c_option)
                opcion_seleccionada = 0
            }
            parent.getItemAtPosition(1).toString() -> {
                imagen.setImageResource(R.drawable.dgtic_volumen_inicial_boyle)
                tv_a.setText(R.string.tv_p1)
                tv_b.setText(R.string.tv_p2)
                tv_c.setText(R.string.tv_v2_boyle)
                opcion_seleccionada = 1
            }
            parent.getItemAtPosition(2).toString() -> {
                imagen.setImageResource(R.drawable.dgtic_volumen_inicial_charles)
                tv_a.setText(R.string.tv_t1)
                tv_b.setText(R.string.tv_t2)
                tv_c.setText(R.string.tv_v2_charles)
                opcion_seleccionada = 2
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    fun clickCalcular(view: View) {
        val etn_a:EditText = findViewById(R.id.ETN_a)
        val etn_b:EditText = findViewById(R.id.ETN_b)
        val etn_c:EditText = findViewById(R.id.ETN_c)

        if(!etn_isEmpty(etn_a) and !etn_isEmpty(etn_b) and !etn_isEmpty(etn_c)){
            val numero_a = etn_a.text.toString().toFloat()
            val numero_b = etn_b.text.toString().toFloat()
            val numero_c = etn_c.text.toString().toFloat()

            if(opcion_seleccionada != -1){
                if(opcion_seleccionada == 0){
                    val resultado = ecuacion_segundo_grado(numero_a,numero_b,numero_c)
                    if(resultado.getString("valido") == "true"){
                        val intent = Intent(this, IntentResultados::class.java)
                        intent.putExtras(resultado)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@MainActivity,resultado.getString("valido"),Toast.LENGTH_SHORT).show()
                    }
                }
                if(opcion_seleccionada == 1){
                    val resultado = ley_de_boyle(numero_a,numero_b,numero_c)
                    if(resultado.getString("valido") == "true"){
                        val intent = Intent(this, IntentResultados::class.java)
                        intent.putExtras(resultado)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@MainActivity,resultado.getString("valido"),Toast.LENGTH_SHORT).show()
                    }
                }
                if(opcion_seleccionada == 2){
                    val resultado = ley_de_charles(numero_a,numero_b,numero_c)
                    if(resultado.getString("valido") == "true"){
                        val intent = Intent(this, IntentResultados::class.java)
                        intent.putExtras(resultado)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@MainActivity,resultado.getString("valido"),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                print("Opcion no valida")
            }

        }else{
            Toast.makeText(this@MainActivity,getString(R.string.toast_campo_vacio),Toast.LENGTH_SHORT).show()
            if(etn_isEmpty(etn_a)) etn_a.error = resources.getString(R.string.etn_error_valorRequerido)
            if(etn_isEmpty(etn_b)) etn_b.error = resources.getString(R.string.etn_error_valorRequerido)
            if(etn_isEmpty(etn_c)) etn_c.error = resources.getString(R.string.etn_error_valorRequerido)
        }

    }

    private fun etn_isEmpty(et_x:EditText): Boolean {
        return et_x.text.isEmpty()
    }

    private fun ecuacion_segundo_grado(numero_a:Float, numero_b:Float, numero_c:Float):Bundle{
        var es_valido = "false"
        var res = 0f

        if( (numero_b*numero_b) < (4 * numero_a * numero_c) ){
            es_valido = resources.getString(R.string.toast_raices_imaginarias)
        }
        if(numero_a == 0f){
            es_valido = resources.getString(R.string.toast_a_es_cero)
        }
        if( es_valido == "false"){
            res = (-1)*(numero_b) + sqrt((numero_b*numero_b)-4*numero_a*numero_c)
            res = res/2*numero_a
            es_valido = "true"
        }

        val parametros = Bundle()
        parametros.apply{
            putString("formula",formula)
            putFloat("res",res)
            putString("valido",es_valido)
        }
        return parametros
    }
    private fun ley_de_boyle(numero_a:Float, numero_b:Float, numero_c:Float):Bundle{
        var es_valido = "false"
        var res = 0f

        if( numero_a == 0f ){
            es_valido = resources.getString(R.string.toast_p1_es_cero)
        }
        if( es_valido == "false"){
            res = (numero_b * numero_c)/numero_a
            es_valido = "true"
        }

        val parametros = Bundle()
        parametros.apply{
            putString("formula",formula)
            putFloat("res",res)
            putString("valido",es_valido)
        }
        return parametros
    }
    private fun ley_de_charles(numero_a:Float, numero_b:Float, numero_c:Float):Bundle{
        var es_valido = "false"
        var res = 0f

        if( numero_b == 0f ){
            es_valido = resources.getString(R.string.toast_t2_es_cero)
        }
        if( es_valido == "false"){
            res = (numero_a * numero_c)/numero_b
            es_valido = "true"
        }

        val parametros = Bundle()
        parametros.apply{
            putString("formula",formula)
            putFloat("res",res)
            putString("valido",es_valido)
        }
        return parametros
    }
}