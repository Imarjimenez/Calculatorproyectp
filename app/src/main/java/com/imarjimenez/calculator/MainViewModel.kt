package com.imarjimenez.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var oper: Int = 0

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun actualizarOperador(nuevoOper: Int) {
        oper = nuevoOper
    }

    fun calcularResultado(numero1Text: String, numero2Text: String): Double {
        // Verificar si al menos uno de los números tiene más de un punto decimal
        if (numero1Text.count { it == '.' } > 1 || numero2Text.count { it == '.' } > 1) {
             _errorMessage.postValue("Al menos uno de los números tiene más de un punto decimal")
            return Double.NaN
        }
        if (numero1Text.isEmpty() || numero2Text.isEmpty()) {
            _errorMessage.postValue( "Al menos un campo está vacío")
            return Double.NaN
        }

        // Convertir los números a Double
        val numero1 = numero1Text.toDouble()
        val numero2 = numero2Text.toDouble()

        // Verificar si se intenta dividir por cero
        if (numero2 == 0.0 && oper == 4) {
            _errorMessage.postValue("La División entre cero no es posible")
            return Double.NaN
        }
        _errorMessage.postValue(null)
        var resultado = 0.0

        // Realizar la operación según el operador seleccionado
        when (oper) {
            1 -> resultado = numero1 + numero2
            2 -> resultado = numero1 - numero2
            3 -> resultado = numero1 * numero2
            4 -> resultado = numero1 / numero2
        }

        // Log de depuración
        Log.d("MainViewModel", "Mensaje de depuración: $resultado")

        return resultado
    }

}