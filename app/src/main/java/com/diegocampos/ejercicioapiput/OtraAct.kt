package com.diegocampos.ejercicioapiput

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegocampos.ejercicioapiput.databinding.ActivityMainBinding
import com.diegocampos.ejercicioapiput.databinding.ActivityOtraBinding

class OtraAct : AppCompatActivity() {

    lateinit var binding: ActivityOtraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var usuarioLogeado:String = intent.getStringExtra("usuario").toString()

        binding.textView2.text = "Bienvenido/a $usuarioLogeado"
    }
}