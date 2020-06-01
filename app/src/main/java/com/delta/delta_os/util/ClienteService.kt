package com.delta.delta_os.util

import com.delta.delta_os.bean.Aparelho
import com.delta.delta_os.bean.Cliente

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClienteService {
    @GET("list")
    fun getClientes(): Call<List<Cliente>>
    @GET("listAparelho")
    fun getAparelho(): Call<List<Aparelho>>
    @POST("doCreate")
    fun addCliente(@Body listCliente:List<Cliente>) :Call <List<Cliente>>
}