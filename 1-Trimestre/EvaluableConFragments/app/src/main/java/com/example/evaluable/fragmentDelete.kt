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

class fragmentDelete : Fragment() {
    private lateinit var codigo: EditText
    private lateinit var nombre: EditText
    private lateinit var equipo: EditText
    private lateinit var valor: EditText
    private lateinit var bDelete: Button
    private lateinit var bVolver: Button
   

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delete, container, false)

        // Inicializar vistas
        codigo = view.findViewById(R.id.codigo)
        nombre = view.findViewById(R.id.nombre)
        equipo = view.findViewById(R.id.equipo)
        valor = view.findViewById(R.id.mercadoValor)
        bDelete = view.findViewById(R.id.deleteButton)
        bVolver = view.findViewById(R.id.volver)
        // Configurar listeners
        bDelete.setOnClickListener { eliminarRegistro() }
        bVolver.setOnClickListener { volverAtras() }

        return view
    }

    private fun limpiar() {
        codigo.text.clear()
        nombre.text.clear()
        equipo.text.clear()
        valor.text.clear()
    }

    private fun eliminarRegistro() {
        if (codigo.text.toString().isNotEmpty()) {
            val fantasy = FantasyOpenHelper(requireContext(), "Liga", null, 1)
            val bd = fantasy.writableDatabase

            val reg = bd.delete("Liga", "codigo=${codigo.text.toString().toInt()}", null)
            if (reg == 1) {
                muestraMensaje("Registro eliminado correctamente")
            } else {
                muestraMensaje("No se ha podido eliminar el registro porque no existe")
            }
            bd.close()
            limpiar()
        } else {
            muestraMensaje("El campo no puede estar vacío")
        }
    }

    private fun volverAtras() {
        requireActivity().onBackPressed()
    }

    private fun muestraMensaje(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }
}
