package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val texto = findViewById<TextView>(R.id.textv)
        val botonir = findViewById<Button>(R.id.buttone)
        botonir.setOnClickListener(){
            if(texto.text.toString().isNotBlank()) {
                val intento1 = Intent(this, Segunda::class.java);
                intento1.putExtra("nombre",texto.text.toString())
                startActivity(intento1)
            }
            else{
                Toast.makeText(this,"el campo está en blanco", Toast.LENGTH_LONG).show()
            }
        }




    }


}