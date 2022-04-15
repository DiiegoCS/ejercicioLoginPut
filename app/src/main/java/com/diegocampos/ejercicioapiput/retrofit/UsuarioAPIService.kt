package com.diegocampos.ejercicioapiput.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioAPIService {

    @GET("{json}")
    fun obtenerUsuario(@Path("json") json: String): Call<Usuario>

    @PUT("bd/{item}.json")
    fun agregarUsuario(@Path("item") item: Int, @Body usuario: Usuario): Call<UsuarioInfo>

}