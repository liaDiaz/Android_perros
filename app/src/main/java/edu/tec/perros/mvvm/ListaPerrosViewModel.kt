package edu.tec.perros.mvvm


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.tec.perros.pattern.RetrofitSingleton
import edu.tec.perros.response.PerroResponse
import edu.tec.perros.service.PerrosAPIService

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPerrosViewModel : ViewModel() {
    //la clase del vieMOdel
    var liveData : MutableLiveData<List<String>>
    //mandrle datos a la vista

    init {
        liveData = MutableLiveData()
    }
    // el observer es para cambios de datos a la vista
    fun getLiveDataObserver(): MutableLiveData<List<String>>{
        return liveData
    }




    fun perroAPICall(raza:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitSingleton.getRetrofit().create(PerrosAPIService::class.java)
                .getPerrosPorRaza("$raza/images")
            call.enqueue(object : Callback<PerroResponse> {
                override fun onResponse(call: Call<PerroResponse>, response: Response<PerroResponse>) {
                    liveData.postValue(response.body()?.imagenes?: emptyList())
                }

                override fun onFailure(call: Call<PerroResponse>, t: Throwable) {
                    liveData.postValue(emptyList())
                }

            })
        }
    }
}