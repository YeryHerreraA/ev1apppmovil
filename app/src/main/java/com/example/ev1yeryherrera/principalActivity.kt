package com.example.ev1yeryherrera

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class principalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //REFERENCIAS A LOS WIDGETS

        val tv_principal = findViewById<TextView>(R.id.tv_principal)
        val sp_principal = findViewById<Spinner>(R.id.sp_principal)
        val blv_principal = findViewById<ListView>(R.id.lv_principal)
        val btn_nueva_lista_principal = findViewById<Button>(R.id.btn_nueva_lista_principal)

        val correo:String = intent.getStringExtra("correo").toString()
        tv_principal.setText("Bienvenido $correo")

        //ARRAY ADAPTER SPINNER


        //GENERACION DEL SPINNER

        val arrayAdapterSpinner: ArrayAdapter<*>
        //definir arreglo de datos
        var meslista = ArrayList<String>()
        meslista.add("Seleccione el mes que creo la lista")
        meslista.add("Enero")
        meslista.add("Febrero")
        meslista.add("Marzo")
        meslista.add("Abril")
        meslista.add("Mayo")
        meslista.add("Junio")
        meslista.add("Julio")
        meslista.add("Agosto")
        meslista.add("Septiembre")
        meslista.add("Octubre")
        meslista.add("Noviembre")
        meslista.add("Diciembre")
        arrayAdapterSpinner = ArrayAdapter(this@principalActivity, android.R.layout.simple_spinner_dropdown_item, meslista)
        sp_principal.adapter = arrayAdapterSpinner

        //array adapter listview
        // generacion de listview
        val arrayAdapterListview: ArrayAdapter<*>
        //defino un arreglo de datos
        var listas = ArrayList<String>()
        listas.add("Lista numero 1")
        listas.add("Lista numero 2")
        listas.add("Lista numero 3")
        listas.add("Lista numero 4")
        listas.add("Lista numero 5")
        listas.add("Lista numero 6")
        //inflar el listview
        arrayAdapterListview = ArrayAdapter(this@principalActivity, android.R.layout.simple_list_item_1, listas)
        blv_principal.adapter = arrayAdapterListview


        //metodo onclick listview
        blv_principal.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, posicion: Int, id: Long) {
                println("${posicion} ${id} ${listas[posicion]}")
                val intent = Intent(this@principalActivity,nueva_listaActivity::class.java)
                intent.putExtra("lista",listas[posicion])
                startActivity(intent)
            }

        }

        //evento boton añadir

        btn_nueva_lista_principal.setOnClickListener {
            startActivity(Intent(this@principalActivity,nueva_listaActivity::class.java))
        }

    }
}