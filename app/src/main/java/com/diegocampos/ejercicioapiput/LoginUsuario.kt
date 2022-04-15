package com.diegocampos.ejercicioapiput

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.diegocampos.ejercicioapiput.databinding.ActivityMainBinding

class LoginUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarse.setOnClickListener {
            val intent:Intent = Intent(applicationContext, RegistroUsuario::class.java)
            startActivity(intent)
        }


    }
}