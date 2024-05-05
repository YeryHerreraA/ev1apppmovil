package com.example.ev1yeryherrera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ev1yeryherrera.room.Db
import com.example.ev1yeryherrera.room.entity.Lista
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.security.Principal

class editarregistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminarregistro)
        val room = Room.databaseBuilder(this, Db::class.java,"database-ciisa").allowMainThreadQueries().build()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Referencia a los widgets
        val til_name_list = findViewById<TextInputLayout>(R.id.til_name_list)
        val til_description_list = findViewById<TextInputLayout>(R.id.til_description_list)
        val btn_editar_pedido = findViewById<Button>(R.id.btn_editar_pedido)

        val correo:String = intent.getStringExtra("correo").toString()


       btn_editar_pedido.setOnClickListener{
           var name = til_name_list.editText?.text.toString()
           var description = til_description_list.editText?.text.toString()
           var lista = Lista(name,description,correo)
           lifecycleScope.launch {
            val id = room.daoLista().agregarLista(lista)
               if (id>0){
                   Toast.makeText(this@editarregistroActivity,"Lista agregada correctamente",Toast.LENGTH_SHORT).show()
                   val intent = Intent(this@editarregistroActivity,Principal::class.java)
                   intent.putExtra("correo",correo)
                   startActivity(intent)
               }
           }
       }
    }
}