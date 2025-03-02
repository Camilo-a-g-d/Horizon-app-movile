package com.example.myapplication
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RecuperacionActivity : AppCompatActivity() {

    private lateinit var viewFlipper: ViewFlipper
    private lateinit var etCorreo: EditText
    private lateinit var etCodigo: EditText
    private lateinit var etNuevaContrasena: EditText
    private lateinit var etConfirmarContrasena: EditText
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        viewFlipper = findViewById(R.id.viewFlipper)
        etCorreo = findViewById(R.id.etCorreo)
        etCodigo = findViewById(R.id.etCodigo)
        etNuevaContrasena = findViewById(R.id.etNuevaContrasena)
        etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena)

        prefs = getSharedPreferences("RecuperacionCache", MODE_PRIVATE)

        findViewById<Button>(R.id.btnEnviarCodigo).setOnClickListener {
            val correo = etCorreo.text.toString().trim()

            if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
            } else {
                val codigoRecuperacion = (100000..999999).random().toString()
                prefs.edit().putString("codigo", codigoRecuperacion).apply()
                Toast.makeText(this, "Código enviado: $codigoRecuperacion", Toast.LENGTH_LONG).show()
                viewFlipper.showNext() // Ir a la pantalla del código
            }
        }

        findViewById<Button>(R.id.btnValidarCodigo).setOnClickListener {
            val codigoIngresado = etCodigo.text.toString().trim()
            val codigoGuardado = prefs.getString("codigo", "")

            if (codigoIngresado == codigoGuardado) {
                Toast.makeText(this, "Código correcto", Toast.LENGTH_SHORT).show()
                viewFlipper.showNext() // Ir a la pantalla de nueva contraseña
            } else {
                Toast.makeText(this, "Código incorrecto", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnConfirmar).setOnClickListener {
            val nuevaContrasena = etNuevaContrasena.text.toString().trim()
            val confirmarContrasena = etConfirmarContrasena.text.toString().trim()

            if (nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Toast.makeText(this, "Ingrese ambas contraseñas", Toast.LENGTH_SHORT).show()
            } else if (nuevaContrasena != confirmarContrasena) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Contraseña actualizada con éxito", Toast.LENGTH_LONG).show()
                prefs.edit().clear().apply()
                finish()
            }
        }
    }
}