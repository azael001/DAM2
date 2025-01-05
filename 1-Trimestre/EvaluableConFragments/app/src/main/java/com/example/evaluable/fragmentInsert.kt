package com.example.evaluable

import android.content.ContentValues
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

class fragmentInsert : Fragment() {
    private lateinit var codigo: EditText
    private lateinit var nombre: EditText
    private lateinit var equipo: EditText
    private lateinit var valor: EditText
    private lateinit var bInsert: Button
    private lateinit var bVolver: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert, container, false)

        // Inicializar vistas
        codigo = view.findViewById(R.id.codigo)
        nombre = view.findViewById(R.id.nombre)
        equipo = view.findViewById(R.id.equipo)
        valor = view.findViewById(R.id.mercadoValor)
        bInsert = view.findViewById(R.id.insertButton)
        bVolver = view.findViewById(R.id.volver)



        // Configurar listeners
        bInsert.setOnClickListener { insertarRegistro() }
        bVolver.setOnClickListener { volverAtras() }

        return view
    }

    private fun limpiar() {
        codigo.text.clear()
        nombre.text.clear()
        equipo.text.clear()
        valor.text.clear()
    }

    private fun insertarRegistro() {
        val fantasy = FantasyOpenHelper(requireContext(), "Liga", null, 1)
        val bd = fantasy.writableDatabase
        val registro = ContentValues()

        registro.put("codigo", codigo.text.toString().toInt())
        registro.put("nombre", nombre.text.toString())
        registro.put("equipo", equipo.text.toString())
        registro.put("valor_Mercado", valor.text.toString().toInt())

        bd.insert("Liga", null, registro)
        bd.close()

        muestraMensaje("Insertado correctamente")
        limpiar()
    }

    private fun volverAtras() {
        requireActivity().onBackPressed()
    }

    private fun muestraMensaje(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }


}
