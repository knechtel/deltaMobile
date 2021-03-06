package com.delta.delta_os.service

import com.delta.delta_os.bean.Cliente
import com.delta.delta_os.dto.AparelhoDto
import com.delta.delta_os.dto.ClienteDto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClienteService {
    @GET("list")
    fun getClientes(): Call<List<Cliente>>
    @GET("listAparelho")
    fun getAparelho(): Call<List<AparelhoDto>>
    @POST("doCreate")
    fun addCliente(@Body listCliente:List<Cliente>) :Call <List<Cliente>>
    @POST("doMergeCliente")
    fun mergeCliente(@Body listCliente:List<ClienteDto>) :Call <List<ClienteDto>>
}