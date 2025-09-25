package com.example.miprimeraapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val startRegister = findViewById<TextView>(R.id.link_registrar)
        startRegister.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        val etEmail = findViewById<TextView>(R.id.input_correo)
        val etPassword = findViewById<TextView>(R.id.input_contrasena)
        val btnLogin = findViewById<TextView>(R.id.btn_iniciar_sesion)

        btnLogin.setOnClickListener {
            comparacionLogin(etEmail, etPassword)
        }
    }
    private fun comparacionLogin(etEmail: EditText, etPassword: EditText) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")
        if (email == savedEmail && password == savedPassword) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
        }

}