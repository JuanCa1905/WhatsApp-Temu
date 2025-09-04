package com.example.miprimeraapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        sharedPreferences = getSharedPreferences("RegistroPrefs", MODE_PRIVATE)
    }
    private fun setupOnClickListener() {
        val etNombres = findViewById<EditText>(R.id.input_nombre)
        val etApellidos = findViewById<EditText>(R.id.input_apellido)
        val etCorreo = findViewById<EditText>(R.id.input_correo)
        val etTelefono = findViewById<EditText>(R.id.input_telefono)
        val etContrasena = findViewById<EditText>(R.id.input_contrasena)
        val etConfirmarContrasena = findViewById<EditText>(R.id.input_confirmar_contrasena)
        val btnRegistrar = findViewById<Button>(R.id.btn_registrar)

        btnRegistrar.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (validarCampos(nombres, apellidos, correo, telefono, contrasena, etConfirmarContrasena.text.toString().trim())) {
                guardarDatosRegistro(nombres, apellidos, correo, telefono, contrasena)
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
    private fun validarCampos(nombres: String, apellidos: String, correo: String, telefono: String, contrasena: String, confirmarContrasena: String): Boolean {
        if(nombres.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if(apellidos.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if(correo.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su correo", Toast.LENGTH_SHORT).show()
            return false
        }
        if(telefono.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su telefono", Toast.LENGTH_SHORT).show()
            return false
        }
        if(contrasena.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if(confirmarContrasena.isEmpty()){
            Toast.makeText(this, "Por favor confirme su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun guardarDatosRegistro(nombres: String, apellidos: String, correo: String, telefono: String, contrasena: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nombres", nombres)
        editor.putString("apellidos", apellidos)
        editor.putString("correo", correo)
        editor.putString("telefono", telefono)
        editor.putString("contrasena", contrasena)
        editor.apply()

    }
}