package edu.tec.perros.service

import edu.tec.perros.response.PerroResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

//lo que hace el service es hacer la conexion de tu servidor con el sitio web

interface PerrosAPIService {

    //suspend fun es para que pueda correr esto de la corutinaa
    @GET
     fun getPerrosPorRaza(@Url url: String):
            Call<PerroResponse>
    // EL call es para no correrlo en el hilo principal y el call es para tener acceso al llamado que hicmos
}