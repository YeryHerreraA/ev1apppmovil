package com.example.ev1yeryherrera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class nueva_listaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_lista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //REFERENCIAS A LOS WIDGETS

        val nombre_nueva_lista = findViewById<TextInputLayout>(R.id.nombre_nueva_lista)
        val detalle_nueva_lista = findViewById<TextInputLayout>(R.id.detalle_nueva_lista)
        val btn_editar_pedido = findViewById<Button>(R.id.btn_editar_pedido)
        val btn_eliminar_pedido = findViewById<Button>(R.id.btn_eliminar_pedido)

        val lista:String = intent.getStringExtra("lista").toString()
        nombre_nueva_lista.editText?.setText(lista)

        //ACCION A REALIZAR TRAS PRESIONAR EL BOTON

        btn_eliminar_pedido.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmacion")
            builder.setMessage("¿Estas seguro de eliminar?")
            builder.setPositiveButton(android.R.string.ok){ dialog, wich ->

//ACCION A REALIZAR SI PRESIONA OK

                Toast.makeText(this,"Elemento eliminado",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,principalActivity::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("Cancelar",null)
            builder.setNeutralButton("No lo se",null)
            builder.show()

        }
    }
}