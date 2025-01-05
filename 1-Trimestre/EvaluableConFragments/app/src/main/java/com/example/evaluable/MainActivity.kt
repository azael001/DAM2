package com.example.evaluable

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val butonRead = findViewById<Button>(R.id.Read)
        val butonDelete = findViewById<Button>(R.id.borrar)
        val butonUpdate = findViewById<Button>(R.id.modificar)
        val butonInsert = findViewById<Button>(R.id.insertar)
        val butonMostrar = findViewById<Button>(R.id.search)
        butonRead.setOnClickListener{
           val fra1 = fragmentRead()
            showfragment(fra1)
        }
        butonDelete.setOnClickListener{
            val fra2 = fragmentDelete()
            showfragment(fra2)
        }
        butonUpdate.setOnClickListener{
            val fra3 = fragmentUpdate()
            showfragment(fra3)
        }
        butonInsert.setOnClickListener{
            val fra4 = fragmentInsert()
            showfragment(fra4)
        }
        butonMostrar.setOnClickListener{
            val fra5 = fragmentShow()
            showfragment(fra5)
        }
    }
    fun showfragment(fragmento: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.fra, fragmento)
        frag.commit()
    }

}
data class Jugadores(val codigo: Int,val nombre: String,val equipo:String,val value: Int)
