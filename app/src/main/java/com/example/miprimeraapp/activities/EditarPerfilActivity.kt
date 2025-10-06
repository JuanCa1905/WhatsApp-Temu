package com.example.miprimeraapp.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.R

class EditarPerfilActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etNuevaContrasena: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)


        val userEmail = sharedPreferences.getString("email", "")


        etNombres = findViewById(R.id.edit_nombres)
        etApellidos = findViewById(R.id.edit_apellidos)
        etCorreo = findViewById(R.id.edit_correo)
        etTelefono = findViewById(R.id.edit_telefono)
        etNuevaContrasena = findViewById(R.id.edit_nueva_contrasena)
        btnGuardar = findViewById(R.id.btn_guardar_cambios)


        cargarDatos(userEmail)


        btnGuardar.setOnClickListener {

            guardarCambios(userEmail)
        }
    }

    private fun cargarDatos(email: String?) {

        if (email.isNullOrEmpty()) return

        val nombres = sharedPreferences.getString("nombres", "")
        val apellidos = sharedPreferences.getString("apellidos", "")
        val telefono = sharedPreferences.getString("telefono", "")


        etNombres.setText(nombres)
        etApellidos.setText(apellidos)
        etCorreo.setText(email)
        etTelefono.setText(telefono)
    }

    private fun guardarCambios(email: String?) {
        if (email.isNullOrEmpty()) {
            Toast.makeText(this, "Error: No se puede guardar sin un email asociado.", Toast.LENGTH_LONG).show()
            return
        }


        val nuevaPassword = etNuevaContrasena.text.toString()
        if (nuevaPassword.isNotEmpty() && nuevaPassword.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPreferences.edit()

        editor.putString("nombres", etNombres.text.toString())
        editor.putString("apellidos", etApellidos.text.toString())
        editor.putString("telefono", etTelefono.text.toString())


        if (nuevaPassword.isNotEmpty()) {

            editor.putString("password", nuevaPassword)
            Toast.makeText(this, "Contraseña actualizada.", Toast.LENGTH_SHORT).show()
        }


        editor.apply()

        Toast.makeText(this, "Perfil actualizado con éxito.", Toast.LENGTH_SHORT).show()
        finish()
    }
}