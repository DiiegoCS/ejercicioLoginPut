package com.diegocampos.ejercicioapiput

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.diegocampos.ejercicioapiput.databinding.ActivityMainBinding
import com.diegocampos.ejercicioapiput.databinding.ActivityRegistroUsuarioBinding
import com.diegocampos.ejercicioapiput.retrofit.RestEngine
import com.diegocampos.ejercicioapiput.retrofit.Usuario
import com.diegocampos.ejercicioapiput.retrofit.UsuarioAPIService
import com.diegocampos.ejercicioapiput.retrofit.UsuarioInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call

class RegistroUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrearUsuario.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {

                var usu= binding.txtCrearUsuario.text.toString()

                var aux2 = validarUsuario(usu)

                if (aux2 == 0){
                    val x: Int = async {
                        cantidadRegistros()
                    }.await()

                    crearRegistro(
                        x, UsuarioInfo(
                            binding.txtCrearNombre.text.toString(),
                            binding.txtCrearRut.text.toString(),
                            binding.txtCrearMail.text.toString(),
                            binding.txtCrearUsuario.text.toString(),
                            binding.txtCrearContrasena.text.toString()
                        )
                    )
                    val intent: Intent = Intent(applicationContext, LoginUsuario::class.java)
                    startActivity(intent)
                }
                else{
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Ups! No se pudo crear el usuario!", Toast.LENGTH_SHORT).show()
                        binding.progressBar2.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun crearRegistro(x: Int, usuarioInfo: UsuarioInfo) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada: UsuarioAPIService =
                RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
            val resultado: Call<UsuarioInfo> = llamada.agregarUsuario(x, usuarioInfo)
            val u:UsuarioInfo? = resultado.execute().body()

            if(u != null){
                runOnUiThread {
                    Toast.makeText(applicationContext, "Usuario Creado Exitósamente!", Toast.LENGTH_SHORT).show()
                    binding.progressBar2.visibility = View.GONE
                }
            }
            else{
                runOnUiThread {
                    Toast.makeText(applicationContext, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                    binding.progressBar2.visibility = View.GONE
                }
            }
        }
    }

    private fun cantidadRegistros():Int {
        val llamada: UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado: Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u:Usuario? = resultado.execute().body()
        return u!!.size
    }

    private fun validarUsuario(usuario:String) : Int{
        val llamada: UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado: Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u:Usuario? = resultado.execute().body()

        var aux:Int= 0
        for (i in u!!){
            if (i.usuario == usuario ){
                aux= 1
            }
            else{
                aux= 0
            }
        }
        return aux
    }
}