package edu.tec.perros.pattern

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitSingleton {
    val constante = "Estoy en singleton"
    fun getRetrofit(): Retrofit {
        //esto es para la conecoioon con ek servicio simpre se debe temrianr con diagonal
        //El converter es para convertir el json al local
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/").addConverterFactory(
            GsonConverterFactory.create())
            .build()
    }
}
fun main(args: Array<String>) {
    println(RetrofitSingleton.toString())
    println(RetrofitSingleton.toString())
}