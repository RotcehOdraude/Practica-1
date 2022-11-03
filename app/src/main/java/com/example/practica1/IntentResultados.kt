package com.example.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class IntentResultados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_resultados)

        val bundle = intent.extras
        val formula = bundle?.getString("formula")
        val res = bundle?.getFloat("res")

        val tvFormula = findViewById<TextView>(R.id.tv_resultados_formula)
        val tvResultado = findViewById<TextView>(R.id.tv_resultados_calculo)

        tvFormula.setText(formula)
        tvResultado.setText(res.toString())
    }

    fun regresar(view: View) {
        val intent = Intent(this, MainActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}