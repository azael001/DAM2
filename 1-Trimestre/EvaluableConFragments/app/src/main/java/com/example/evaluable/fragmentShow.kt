package com.example.evaluable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad9.FantasyOpenHelper

class fragmentShow : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bVolver: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show, container, false)

        // Inicializar vistas
        recyclerView = view.findViewById(R.id.rec)
        bVolver = view.findViewById(R.id.volver2)


        // Configurar RecyclerView
        val datos = obtenerJugadores()
        val adaptador = ArrayJugadores(datos) { jugador ->
            Toast.makeText(requireContext(), "Has seleccionado el jugador -> ${jugador.nombre}", Toast.LENGTH_LONG).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adaptador
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        // Configurar botón volver
        bVolver.setOnClickListener { volverAtras() }

        return view
    }

    private fun obtenerJugadores(): Array<Jugadores> {
        val listaJugadores = mutableListOf<Jugadores>()
        val fantasy = FantasyOpenHelper(requireContext(), "Liga", null, 1)
        val bd = fantasy.writableDatabase
        val regis = bd.rawQuery("SELECT codigo, nombre, equipo, valor_Mercado FROM Liga", null)
        if (regis.moveToFirst()) {
            do {
                val codigo = regis.getInt(0)
                val nombre = regis.getString(1)
                val equipo = regis.getString(2)
                val mercadoValor = regis.getInt(3)
                listaJugadores.add(Jugadores(codigo, nombre, equipo, mercadoValor))
            } while (regis.moveToNext())
        }
        regis.close()
        bd.close()
        return listaJugadores.toTypedArray()
    }

    private fun volverAtras() {
        requireActivity().onBackPressed()
    }
}
