package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R.id.text2

class Segunda : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segunda)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
     val tvmensaje=findViewById<TextView>(text2)
     val botonvolver= findViewById<Button>(R.id.button)
     val bundle = intent.extras
     val nombre = bundle?.getString("nombre")
     tvmensaje.text= "hola ${nombre.toString()}"

        botonvolver.setOnClickListener(){
            val intento2 = Intent(this, MainActivity::class.java)
            startActivity(intento2)
        }
    }
}