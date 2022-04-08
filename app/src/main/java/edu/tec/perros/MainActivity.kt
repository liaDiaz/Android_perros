package edu.tec.perros

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.tec.perros.adapter.PerrosAdapter
import edu.tec.perros.databinding.ActivityMainBinding
import edu.tec.perros.mvvm.ListaPerrosViewModel
import edu.tec.perros.pattern.RetrofitSingleton
import edu.tec.perros.response.PerroResponse
import edu.tec.perros.service.PerrosAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// el listener es para que la barra se ejecute o active la fucnionalidad de la barra
class MainActivity : AppCompatActivity(), android.widget.SearchView.OnQueryTextListener {
    private lateinit var adapter: PerrosAdapter
    private lateinit var binding: ActivityMainBinding
    private  val perrosPics = mutableListOf<String>()
    private lateinit var viewModel: ListaPerrosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initViewModel()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        //crea un provider que es la clase que resulvel para crear el viewModel
        viewModel = ViewModelProvider(this).get(ListaPerrosViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this,{
            if(!it.isEmpty()){
                //para poder actulizar imagenes
                adapter.setImagenes(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    //esto es para darle al adapter el layorut y el adaptador
    private fun initAdapter(){
        adapter = PerrosAdapter(perrosPics)
        // esto es solo para la conexion del renglon al layout +
        binding.perros.layoutManager =  LinearLayoutManager(this)
        binding.perros.adapter = adapter

       // buscarPerrosPorRaza("labrador")
        //unir o darle el listener a la barra de busqueda
        binding.busqueda.setOnQueryTextListener(this)


    }


    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

   /* private fun buscarPerrosPorRaza(raza: String){
        //crear un courtine scope para reallizar o definir un bloqueo para poder esperar a que otro termine

        // el dispach IO se utiliza para y el defult
        CoroutineScope(Dispatchers.IO).launch {
            val llamado = RetrofitSingleton.getRetrofit().create(PerrosAPIService::class.java).getPerrosPorRaza("$raza/images")
            //cachar la respuesta o el body del response y aqui debe todas las cadenas o lo que le pedimos
            val perrosResponse: PerroResponse? = llamado.body()
            // es es para que la corrutina pueda modificar la interfaz grafica sin afectar el hilo principal de la interfaz frafica
            runOnUiThread {
                if (llamado.isSuccessful) {
                    // si esto esta vacio la lista regresa nulo sino la lista o repsuesta
                    val imagenes: List<String> = perrosResponse?.imagenes ?: emptyList()
                    perrosPics.clear()
                    perrosPics.addAll(imagenes)

                    //forzar a que se actulaice la interfaz

                    adapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
                hideKeyboard()
            }

        }

    }
    */

    // este es mejor porque este es cuando ya da el aceptar a la busqueda

    override fun onQueryTextSubmit(searchString: String?): Boolean {
        if(!searchString.isNullOrEmpty()){
           // buscarPerrosPorRaza(searchString.lowercase())
            consultaPerros(searchString)
        }
       return true
    }

    // no es muy recomdable por que es con cada tecleo de letra

    fun consultaPerros(searchString: String?){
        if(!searchString.isNullOrEmpty()){
            viewModel.perroAPICall(searchString)
            hideKeyboard()
        }

    }
    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }


}