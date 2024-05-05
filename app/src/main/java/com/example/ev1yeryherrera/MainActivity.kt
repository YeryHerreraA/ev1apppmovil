package com.example.ev1yeryherrera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ev1yeryherrera.room.Db
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.security.Principal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //INICIALIZAMOS LA DB
        val room = Room.databaseBuilder(this, Db::class.java,"database-ciisa").allowMainThreadQueries().build()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //REFERENCIAS A LOS WIDGETS

        val til_correo_login = findViewById<TextInputLayout>(R.id.til_correo_login)
        val til_clave_login = findViewById<TextInputLayout>(R.id.til_clave_login)
        val sw_recordar = findViewById<Switch>(R.id.sw_recordar)
        val btn_iniciar_sesion_login = findViewById<Button>(R.id.btn_iniciar_sesion_login)
        val btn_registrarse_login = findViewById<Button>(R.id.btn_registrarse_login)

        //SHARED PREFERENCES
        val preferencias = getSharedPreferences("datos",Context.MODE_PRIVATE)
        // si recordamos el valor lo vamos a setear
        til_correo_login.editText?.setText(preferencias.getString("correo",""))

        //obtener valores de los campos de texto

       btn_iniciar_sesion_login.setOnClickListener {
          var correo = til_correo_login.editText?.text.toString()
           var clave = til_clave_login.editText?.text.toString()
           var contador = validarcampos()
           var isRecordar = sw_recordar.isChecked
           println("El SWITCH ESTA: $isRecordar")
           if (contador == 0){
                if (isRecordar){
                    val editor = preferencias.edit()
                    editor.putString("correo",correo)
                    editor.commit()
                }

               lifecycleScope.launch {
                   val response=room.daoUsuario().login(correo,clave)
                   if (response.size ==1){
                       Toast.makeText(this@MainActivity,"Login exitoso", Toast.LENGTH_LONG).show()
                       val intent =Intent(this@MainActivity,Principal::class.java)
                   }
               }

               Toast.makeText(this, "Login Exitoso", Toast.LENGTH_LONG).show()
           val intent = Intent(this@MainActivity,principalActivity::class.java)
               intent.putExtra("correo",correo)
               startActivity(intent)

           }
           else{
               til_correo_login.error = "Clave o correo invalido "
               til_clave_login.error = "Clave o correo invalido "
           }
       }
        btn_registrarse_login.setOnClickListener {
            startActivity(Intent(this@MainActivity,registroActivity::class.java))

        }
    }

    fun validarcampos():Int{
        var contador:Int = 0
        val til_correo_login = findViewById<TextInputLayout>(R.id.til_correo_login)
        val til_clave_login = findViewById<TextInputLayout>(R.id.til_clave_login)
        var correo = til_correo_login.editText?.text.toString()
        var clave = til_clave_login.editText?.text.toString()
        val validate = validate()

        //validaciones para que los campos no esten vacios

        if (validate.validarCampoNulo(correo)){
            til_correo_login.error = getString(R.string.mensaje_campo_vacio)
            contador++
        }
        else {
            //validacion para el correo valido

            if (validate.validarFormatoCorreo(correo)){
                til_correo_login.error = ("El correo no cumple el formato usuario@correo.cl")
                contador++
            }
            til_correo_login.error =("")
        }
        if (validate.validarCampoNulo(clave)){
            til_clave_login.error = getString(R.string.mensaje_campo_vacio)
            contador++
        }
        else {
            til_clave_login.error =("")
        }


        return contador
    }

    //FUNCIONES DE ESTADO DE CICLO
    override fun onDestroy() {

        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
        println("onDestroy")

        super.onDestroy()

    }
    override fun onStart() {

        super.onStart()

        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
        println("onStart")

    }
    override fun onResume() {

        super.onResume()

        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
        println("onResume")

    }
    override fun onRestart() {

        super.onRestart()

        //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
        println("onRestart")

    }
    override fun onPause() {

        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
        println("onPause")

        super.onPause()

    }
    override fun onStop() {

        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
        println("onStop")

        super.onStop()

    }
}