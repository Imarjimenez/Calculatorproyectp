package com.imarjimenez.calculator
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // 0=nada, 1=suma, 2=resta, 3=mult, 4=div
    var oper: Int =0
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
        button_equal = findViewById(R.id.button_equal)
        button_delete = findViewById(R.id.button_delete)
        TextNumber_one = findViewById(R.id.TextNumber_one)
        TextNumber_two = findViewById(R.id.TextNumber_two)
        TextNumber_result = findViewById(R.id.TextNumber_result)
        currentTextView = TextNumber_one // Inicialmente, el foco está en TextNumber_one

        // Configurar los botones para que llamen a la función al hacer clic
        val buttons = arrayOf(
            R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three,
            R.id.button_four, R.id.button_five, R.id.button_six, R.id.button_seven,
            R.id.button_eight, R.id.button_nine, R.id.button_point

        )

        for (buttonId in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onButtonClick(button) }
        }
        button_equal.setOnClickListener {
            val numero1Text = TextNumber_one.text.toString()
            val numero2Text = TextNumber_two.text.toString()

            // Verifica si hay más de un punto decimal en numero1
            if (numero1Text.count { it == '.' } > 1 || numero2Text.count { it == '.' } > 1) {
                Toast.makeText(this, "Al menos uno de los números tiene más de un punto decimal", Toast.LENGTH_LONG).show()
                return@setOnClickListener // Sale de la función sin realizar más cálculos
            }

            var numero1 = numero1Text.toDouble()
            var numero2 = numero2Text.toDouble()

            var resultado = 0.0

            if (numero2 == 0.0 && oper == 4) {
                Toast.makeText(this, "La División entre cero no es posible", Toast.LENGTH_LONG).show()
            } else {
                when (oper) {
                    1 -> resultado = numero1 + numero2
                    2 -> resultado = numero1 - numero2
                    3 -> resultado = numero1 * numero2
                    4 -> resultado = numero1 / numero2
                }
                Log.d(ContentValues.TAG, "Mensaje de depuración: " + resultado)
                TextNumber_result.text = resultado.toString()
            }
        }
        button_delete.setOnClickListener {
            TextNumber_one.setText("")
            TextNumber_two.setText("")
            TextNumber_result.setText("")
            oper = 0


        }
        // Configurar los TextViews para cambiar el TextView enfocado al hacer clic
        TextNumber_one.setOnClickListener { changeCurrentTextView(TextNumber_one) }
        TextNumber_two.setOnClickListener { changeCurrentTextView(TextNumber_two) }
    }

    private fun onButtonClick(button: Button) {
        val buttonText = button.text.toString()
        val currentText = currentTextView.text.toString()
        currentTextView.text = currentText + buttonText
    }

    private fun changeCurrentTextView(textView: TextView) {
        // Cambiar el TextView enfocado cuando se hace clic en él
        currentTextView = textView
    }
    fun clicOperacion(view: View){


        when(view.id){
            R.id.button_plus -> {
                oper = 1
                Log.d("button", "clicked")
            }
            R.id.button_minus -> {
                oper = 2
            }
            R.id.button_times -> {
                oper = 3
            }
            R.id.button_divide -> {
                oper = 4
            }
        }
        currentTextView = TextNumber_two

    }

}






