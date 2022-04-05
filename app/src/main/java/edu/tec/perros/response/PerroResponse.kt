package edu.tec.perros.response

import com.google.gson.annotations.SerializedName
//serilizeName es el convertidor o cachar la respuesta que da el servicio y lo cambie a kotlin

data class PerroResponse(@SerializedName("status") var status: String, @SerializedName("message") var imagenes: List<String>)
