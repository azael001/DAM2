package com.example.evaluable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad9.FantasyOpenHelper

class ArrayJugadores (private val datos: Array<Jugadores>, private val clickListener:(Jugadores) ->Unit): RecyclerView.Adapter<ArrayJugadores.JugadoresViewHolder>() {

    class JugadoresViewHolder(val item: View): RecyclerView.ViewHolder(item) {

        val codeTx = item.findViewById<TextView>(R.id.code)
        val nameTx = item.findViewById<TextView>(R.id.name)
        val teamTx = item.findViewById<TextView>(R.id.team)
        val valueTx = item.findViewById<TextView>(R.id.value)

        fun bindJugadores(jugadores: Jugadores){
            codeTx.text=("Código del jugador: " + jugadores.codigo.toString())
            nameTx.text=("Nombre: "+jugadores.nombre)
            teamTx.text=("Equipo: "+jugadores.equipo)
            valueTx.text=("Valor de mercado: " + jugadores.value.toString())
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadoresViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.listajugadores, parent, false) as LinearLayout
        return JugadoresViewHolder(item)
    }

    override fun onBindViewHolder(holder: JugadoresViewHolder, position: Int) {
        val jugadores = datos[position]
        holder.bindJugadores(jugadores)
        holder.item.setOnClickListener { clickListener(jugadores) }
    }
    override fun getItemCount(): Int =datos.size
}