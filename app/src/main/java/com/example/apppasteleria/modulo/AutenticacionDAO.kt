package com.example.apppasteleria.modulo

import android.content.Context
import android.widget.Toast
import com.example.apppasteleria.modulo.poko.Cliente
import com.example.apppasteleria.modulo.poko.MensajeCliente
import com.example.apppasteleria.modulo.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

object AutenticacionDAO {


    fun iniciarSesionMovil(cliente: Cliente, context: Context) {

        Ion.getDefault(context).conscryptMiddleware.enable(false)

        Ion.with(context)
            .load("POST", "${Constantes.URL_WS}autenticacion/iniciarSesionMovil")
            .setHeader("Content-Type", "application/json")
            .setJsonPojoBody(cliente)
            .asString()
            .setCallback { e, result ->
                if(e == null && result != null){
                    serializarCliente(result, context)
                }else{
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun serializarCliente(json: String, context: Context) {
        val gson = Gson()
        var mensajeCliente:MensajeCliente = gson.fromJson(json, MensajeCliente::class.java)

        Toast.makeText(context, mensajeCliente.respuesta.toString(), Toast.LENGTH_SHORT).show()
    }
}