package com.example.apppasteleria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apppasteleria.databinding.ActivityInicioSesionBinding
import com.example.apppasteleria.modulo.AutenticacionDAO
import com.example.apppasteleria.modulo.poko.Cliente
import com.example.apppasteleria.modulo.poko.MensajeCliente
import com.example.apppasteleria.modulo.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class InicioSesionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding
    private lateinit var cliente:Cliente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnIniciarSesion.setOnClickListener {


            cliente = Cliente()

            cliente.correo = binding.etCorreo.text.trim().toString()
            cliente.contrasenia = binding.etPassword.text.trim().toString()

            if(verificarCampos(cliente)){
                AutenticacionDAO.iniciarSesionMovil(cliente, this@InicioSesionActivity)
            }
        }

    }

    private fun verificarCampos(cliente: Cliente): Boolean{
        var esValido = true

        if(cliente.correo!!.isEmpty() || cliente.correo == null){
            esValido = false
            binding.etCorreo.error = "Campo obligatorio"
        }

        if(cliente.contrasenia!!.isEmpty() || cliente.contrasenia == null){
            esValido = false
            binding.etPassword.error = "Campo obligatorio"
        }

        return esValido
    }


}