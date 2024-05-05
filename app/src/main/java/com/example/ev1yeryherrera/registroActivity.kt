package com.example.ev1yeryherrera

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ev1yeryherrera.room.Db
import com.example.ev1yeryherrera.room.entity.Usuario
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.util.Calendar

class registroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        //INICIALIZAMOS LA DB
        val room = Room.databaseBuilder(this, Db::class.java,"database-ciisa").allowMainThreadQueries().build()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //REFERENCIAS A LOS WIDGETS

        val til_user_register = findViewById<TextInputLayout>(R.id.til_user_register)
        val til_correo_register = findViewById<TextInputLayout>(R.id.til_correo_register)
        val til_fechanac_register = findViewById<TextInputLayout>(R.id.til_fechanac_register)
        val til_contraseña_register = findViewById<TextInputLayout>(R.id.til_contraseña_register)
        val til_rcontraseña_register = findViewById<TextInputLayout>(R.id.til_rcontraseña_register)
        val sw_aceptar_terminos_registe = findViewById<Button>(R.id.sw_aceptar_terminos_register)
        val btn_register_register = findViewById<Button>(R.id.btn_register_register)
        btn_register_register.setOnClickListener {
            var correo = til_correo_register.editText?.text.toString()
            var nombre = til_user_register.editText?.text.toString()
            var fecha = til_fechanac_register.editText?.text.toString()
            var password = til_contraseña_register.editText?.text.toString()

            val usuario = Usuario(correo,nombre,fecha,password)
            lifecycleScope.launch {
                val id = room.daoUsuario().agregarUsuario(usuario)
                if (id>0){
                    Log.d("IDUSER",id.toString())
                    Toast.makeText(this@registroActivity, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@registroActivity,principalActivity::class.java))
                }else{
                    Toast.makeText(this@registroActivity, "Error al agregar el usuario", Toast.LENGTH_SHORT).show()
                }

            }

        }

        //Obtenemos la instancia del calendario actual del sistema

        val cal = Calendar.getInstance()
        // generamos un listener
        val ListenerFecha = DatePickerDialog.OnDateSetListener { datepicker, anyo, mes, dia ->
            var mes = mes+1
            var smes = "${mes}"
            var sdia = "${dia}"

                if (mes<10){
                smes  = "0${mes}"
                }
                if (dia<10){
                sdia  = "0${dia}"
                }
            til_fechanac_register.editText?.setText("$sdia/$smes/$anyo")

        }
        // generar el evento que al presionar el campo aparesca el picker
        til_fechanac_register.editText?.setOnClickListener {
            //mostrar el picker
            DatePickerDialog(this,ListenerFecha,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}