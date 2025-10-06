package com.example.miprimeraapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.R

class PerfilActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var valorNombre: TextView
    private lateinit var valorApellido: TextView
    private lateinit var valorCorreo: TextView
    private lateinit var valorTelefono: TextView
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // 1. Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        // 2. Enlazar vistas del layout de perfil
        valorNombre = findViewById(R.id.valor_nombre)
        valorApellido = findViewById(R.id.valor_apellido)
        valorCorreo = findViewById(R.id.valor_correo)
        valorTelefono = findViewById(R.id.valor_telefono)
        btnEditar = findViewById(R.id.btn_editar_perfil)

        // 3. Configurar el botón para ir a la edición
        btnEditar.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Cargar los datos cada vez que la Activity vuelve (por si se editaron)
        cargarDatosPerfil()
    }

    private fun cargarDatosPerfil() {
        // Obtenemos el email del usuario logueado (la clave)
        val emailDeRegistro = sharedPreferences.getString("email", "Correo no encontrado") ?: "Correo no encontrado"

        // Leer los datos del usuario
        val nombres = sharedPreferences.getString("nombres", "N/A")
        val apellidos = sharedPreferences.getString("apellidos", "N/A")
        val telefono = sharedPreferences.getString("telefono", "N/A")

        // Mostrar en los TextViews
        valorNombre.text = nombres
        valorApellido.text = apellidos
        valorCorreo.text = emailDeRegistro // Usamos el email guardado al loguear
        valorTelefono.text = telefono
    }
}