package com.imarjimenez.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    var oper: Int = 0
    lateinit var TextNumber_one: TextView
    lateinit var TextNumber_two: TextView
    lateinit var TextNumber_result: TextView
    lateinit var errorMessageTextView: TextView
    lateinit var button_equal: Button
    lateinit var button_delete: Button
    lateinit var currentTextView: TextView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        button_equal = findViewById(R.id.button_equal)
        button_delete = findViewById(R.id.button_delete)
        TextNumber_one = findViewById(R.id.TextNumber_one)
        TextNumber_two = findViewById(R.id.TextNumber_two)
        TextNumber_result = findViewById(R.id.TextNumber_result)
        currentTextView = TextNumber_one
        errorMessageTextView = findViewById(R.id.errorMessageTextView)

        val buttons = arrayOf(
            R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three,
            R.id.button_four, R.id.button_five, R.id.button_six, R.id.button_seven,
            R.id.button_eight, R.id.button_nine, R.id.button_point
        )

        for (buttonId in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onButtonClick(button) }
        }
        mainViewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                // Hay un mensaje de error, muestra el TextView y establece el mensaje
                errorMessageTextView.visibility = View.VISIBLE
                errorMessageTextView.text = errorMessage
            } else {
                // No hay error, oculta el TextView
                errorMessageTextView.visibility = View.GONE
            }
        })

        button_equal.setOnClickListener {
            val numero1Text = TextNumber_one.text.toString()
            val numero2Text = TextNumber_two.text.toString()


            val resultado = mainViewModel.calcularResultado(numero1Text, numero2Text)



            TextNumber_result.text = resultado.toString()
        }

        button_delete.setOnClickListener {
            TextNumber_one.text = ""
            TextNumber_two.text = ""
            TextNumber_result.text = ""
            oper = 0
        }

        TextNumber_one.setOnClickListener { changeCurrentTextView(TextNumber_one) }
        TextNumber_two.setOnClickListener { changeCurrentTextView(TextNumber_two) }
    }

    fun clicOperacion(view: View) {
        when (view.id) {
            R.id.button_plus -> {
                mainViewModel.actualizarOperador(1)
            }
            R.id.button_minus -> {
                mainViewModel.actualizarOperador(2)
            }
            R.id.button_times -> {
                mainViewModel.actualizarOperador(3)
            }
            R.id.button_divide -> {
                mainViewModel.actualizarOperador(4)
            }
        }
        currentTextView = TextNumber_two

    }

    private fun onButtonClick(button: Button) {
        val buttonText = button.text.toString()
        val currentText = currentTextView.text.toString()
        currentTextView.text = currentText + buttonText
    }

    private fun changeCurrentTextView(textView: TextView) {
        currentTextView = textView
    }
}