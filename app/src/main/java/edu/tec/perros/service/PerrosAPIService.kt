package edu.tec.perros.service

import edu.tec.perros.response.PerroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

//lo que hace el service es hacer la conexion de tu servidor con el sitio web

interface PerrosAPIService {

    //suspend fun es para que pueda correr esto de la corutinaa
    @GET
    suspend fun getPerrosPorRaza(@Url url: String): Response<PerroResponse>
}