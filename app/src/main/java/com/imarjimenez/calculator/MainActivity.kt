package com.imarjimenez.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var TextNumber_one: TextView
    private lateinit var TextNumber_two: TextView
    private lateinit var TextNumber_result: TextView
    private lateinit var button_equal: Button
    private lateinit var button_delete: Button
    private lateinit var currentTextView: TextView

    // Variable para almacenar el número ingresado actualmente
    private var currentNumber: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Inicialización de elementos de interfaz de usuario
        button_equal = findViewById(R.id.button_equal)
        button_delete = findViewById(R.id.button_delete)
        TextNumber_one = findViewById(R.id.TextNumber_one)
        TextNumber_two = findViewById(R.id.TextNumber_two)
        TextNumber_result = findViewById(R.id.TextNumber_result)
        currentTextView = TextNumber_one

        // Configuración de observadores para el resultado
        mainViewModel.resultado.observe(this, Observer {
            TextNumber_result.text = it
        })

        // Resto del código...

        button_equal.setOnClickListener {
            performCalculation()
        }

        button_delete.setOnClickListener {
            clearInputs()
        }

        // Resto del código...

        //TextNumber_one.setOnClickListener { changeCurrentTextView(TextNumber_one) }
        //TextNumber_two.setOnClickListener { changeCurrentTextView(TextNumber_two) }
    }

    private fun performCalculation() {
        val numero1Text = TextNumber_one.text.toString()
        val numero2Text = TextNumber_two.text.toString()

        // Resto del código...

        mainViewModel.setNumero1(numero1Text.toDouble())
        mainViewModel.setNumero2(numero2Text.toDouble())

        mainViewModel.realizarCalculo()
    }

    private fun clearInputs() {
        TextNumber_one.text = ""
        TextNumber_two.text = ""
        TextNumber_result.text = ""
        currentNumber.clear()
        mainViewModel.setOperacion(0)
    }

    fun clicOperacion(view: View) {
        when (view.id) {
            R.id.button_plus -> {
                mainViewModel.setOperacion(1)
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

    fun onNumberButtonClick(view: View) {
        val button = view as Button
        currentNumber.append(button.text)
        // Mostrar el número actualmente ingresado en el TextView correspondiente
        currentTextView.setText(currentNumber.toString())
    }
}
