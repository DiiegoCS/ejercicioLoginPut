package com.diegocampos.ejercicioapiput.retrofit

class Usuario: ArrayList<UsuarioInfo>()

data class UsuarioInfo(
    val nombre:String,
    val rut: String,
    val mail: String,
    val usuario: String,
    val contraseña: String,

)
