package com.imarjimenez.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var numero1: Double = 0.0
    private var numero2: Double = 0.0
    private var oper: Int = 0

    private val _resultado = MutableLiveData<String>()
    val resultado: LiveData<String>
        get() = _resultado

    fun setNumero1(numero: Double) {
        numero1 = numero
    }

    fun setNumero2(numero: Double) {
        numero2 = numero
    }

    fun setOperacion(operacion: Int) {
        oper = operacion
    }

    fun realizarCalculo() {
        var resultadoCalculado = 0.0

        when (oper) {
            1 -> resultadoCalculado = numero1 + numero2
            2 -> resultadoCalculado = numero1 - numero2
            3 -> resultadoCalculado = numero1 * numero2
            4 -> {
                if (numero2 != 0.0) {
                    resultadoCalculado = numero1 / numero2
                }
            }
        }

        _resultado.value = resultadoCalculado.toString()
    }
}

