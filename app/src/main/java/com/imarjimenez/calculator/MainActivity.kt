package com.imarjimenez.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    lateinit var TextNumber_one: TextView
    lateinit var TextNumber_two: TextView
    lateinit var TextNumber_result: TextView
    lateinit var button_equal: Button
    lateinit var button_delete: Button
    lateinit var currentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Resto del código...

        mainViewModel.resultado.observe(this, Observer {
            TextNumber_result.text = it
        })

        button_equal.setOnClickListener {
            performCalculation()
        }

        button_delete.setOnClickListener {
            clearInputs()
        }

        TextNumber_one.setOnClickListener { changeCurrentTextView(TextNumber_one) }
        TextNumber_two.setOnClickListener { changeCurrentTextView(TextNumber_two) }
    }

    private fun performCalculation() {
        val numero1Text = TextNumber_one.text.toString()
        val numero2Text = TextNumber_two.text.toString()

        // Verifica si hay más de un punto decimal en numero1
        if (numero1Text.count { it == '.' } > 1 || numero2Text.count { it == '.' } > 1) {
            Toast.makeText(this, "Al menos uno de los números tiene más de un punto decimal", Toast.LENGTH_LONG).show()
            return  // Sale de la función sin realizar más cálculos
        }

        mainViewModel.setNumero1(numero1Text.toDouble())
        mainViewModel.setNumero2(numero2Text.toDouble())

        mainViewModel.realizarCalculo()
    }

    private fun clearInputs() {
        TextNumber_one.text = ""
        TextNumber_two.text = ""
        TextNumber_result.text = ""
        mainViewModel.setOperacion(0)
    }

    fun clicOperacion(view: View) {
        when (view.id) {
            R.id.button_plus -> {
                mainViewModel.setOperacion(1)
                Log.d("button", "clicked")
            }
            R.id.button_minus -> {
                mainViewModel.setOperacion(2)
            }
            R.id.button_times -> {
                mainViewModel.setOperacion(3)
            }
            R.id.button_divide -> {
                mainViewModel.setOperacion(4)
            }
        }
        currentTextView = TextNumber_two
    }

    private fun changeCurrentTextView(textView: TextView) {
        // Cambiar el TextView enfocado cuando se hace clic en él
        currentTextView = textView
    }
}




