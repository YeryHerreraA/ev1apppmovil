package com.example.ev1yeryherrera

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

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
        //REFERENCIAS A LOS WIDGETS

        val til_correo_login = findViewById<TextInputLayout>(R.id.til_correo_login)
        val til_clave_login = findViewById<TextInputLayout>(R.id.til_clave_login)
        val btn_iniciar_sesion_login = findViewById<Button>(R.id.btn_iniciar_sesion_login)
        val btn_registrarse_login = findViewById<Button>(R.id.btn_registrarse_login)

        //obtener valores de los campos de texto

       btn_iniciar_sesion_login.setOnClickListener {
          var correo = til_correo_login.editText?.text.toString()
           var clave = til_clave_login.editText?.text.toString()
           var contador = validarcampos()
           if (contador == 0){

               Toast.makeText(this, "Login Exitoso", Toast.LENGTH_LONG).show()
           }


       }
        btn_registrarse_login.setOnClickListener {
            println("presionaste boton registrarse")

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