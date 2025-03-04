package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val tvRecuperar = findViewById<TextView>(R.id.tv_recuperar)
        val tvRegistro = findViewById<TextView>(R.id.tv_registro)

        val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val emailIngresado = etEmail.text.toString().trim()
            val passwordIngresado = etPassword.text.toString().trim()

            val emailGuardado = sharedPref.getString("email", "")
            val passwordGuardada = sharedPref.getString("password", "")

            if (emailIngresado.isEmpty() || passwordIngresado.isEmpty()) {
                Toast.makeText(this, "Por favor, completa los campos", Toast.LENGTH_SHORT).show()
            } else if (emailIngresado == emailGuardado && passwordIngresado == passwordGuardada) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                // Redirigir a PerfilActivity SIN permitir edición
                val intent = Intent(this, PerfilActivity::class.java)
                intent.putExtra("modo_edicion", false) // Enviar un flag que indica que no se puede editar
                startActivity(intent)
                finish() // Cierra LoginActivity para que no pueda volver atrás con el botón de retroceso
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        tvRecuperar.setOnClickListener {
            val intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }
    }
}


