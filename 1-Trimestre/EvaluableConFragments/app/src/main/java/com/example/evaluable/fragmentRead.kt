package com.example.evaluable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.actividad9.FantasyOpenHelper

class fragmentRead : Fragment() {
    private lateinit var codigo: EditText
    private lateinit var nombre: EditText
    private lateinit var equipo: EditText
    private lateinit var valor: EditText
    private lateinit var bRead: Button
    private lateinit var bVolver: Button
    private lateinit var bLimpiar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_read, container, false)

        // Inicializar vistas
        codigo = view.findViewById(R.id.codigo)
        nombre = view.findViewById(R.id.nombre)
        equipo = view.findViewById(R.id.equipo)
        valor = view.findViewById(R.id.mercadoValor)
        bRead = view.findViewById(R.id.readButton)
        bVolver = view.findViewById(R.id.volver)
        bLimpiar = view.findViewById(R.id.limpiar)

        // Configurar listeners
        bRead.setOnClickListener { leerRegistro() }
        bLimpiar.setOnClickListener { limpiar() }
        bVolver.setOnClickListener { volverAtras() }

        return view
    }

    private fun limpiar() {
        codigo.text.clear()
        nombre.text.clear()
        equipo.text.clear()
        valor.text.clear()
    }

    private fun leerRegistro() {
        if (codigo.text.toString().isNotEmpty()) {
            val fantasy = FantasyOpenHelper(requireContext(), "Liga", null, 1)
            val bd = fantasy.writableDatabase
            val regis = bd.rawQuery(
                "SELECT nombre, equipo, valor_Mercado FROM Liga WHERE CODIGO = ${codigo.text.toString().toInt()}",
                null
            )

            if (regis.moveToFirst()) {
                nombre.setText(regis.getString(0))
                equipo.setText(regis.getString(1))
                valor.setText(regis.getInt(2).toString())
            } else {
                muestraMensaje("El código del futbolista no se encuentra en la base de datos")
            }
            regis.close()
            bd.close()
        } else {
            muestraMensaje("El campo código no puede estar vacío")
        }
    }

    private fun volverAtras() {
        requireActivity().onBackPressed()
    }

    private fun muestraMensaje(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }
}
