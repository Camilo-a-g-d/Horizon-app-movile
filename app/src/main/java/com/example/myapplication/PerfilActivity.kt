package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val imgPerfil = findViewById<ImageView>(R.id.img_perfil)
        val tvNombre = findViewById<TextView>(R.id.tv_nombre)
        val tvApellidos = findViewById<TextView>(R.id.tv_apellidos)
        val tvCorreo = findViewById<TextView>(R.id.tv_correo)
        val tvTelefono = findViewById<TextView>(R.id.tv_telefono)
        val btnEditar = findViewById<Button>(R.id.btn_editar)

        val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // Recuperar datos del usuario
        tvNombre.text = "Nombre: " + sharedPref.getString("nombre", "-")
        tvApellidos.text = "Apellidos: " + sharedPref.getString("apellidos", "-")
        tvCorreo.text = "Correo: " + sharedPref.getString("email", "-")
        tvTelefono.text = "Teléfono: " + sharedPref.getString("telefono", "-")

        // Verificar si la edición está permitida
        val modoEdicion = intent.getBooleanExtra("modo_edicion", true)
        if (!modoEdicion) {
            btnEditar.isEnabled = false // Deshabilitar el botón de edición
            btnEditar.alpha = 0.5f // Hacerlo visualmente desactivado
        } else {
            btnEditar.setOnClickListener {
                //val intent = Intent(this, EditarPerfilActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

