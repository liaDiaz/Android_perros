package edu.tec.perros.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.tec.perros.R

class PerrosAdapter(private var imagenes: List<String>): RecyclerView.Adapter<PerroViewHolder>(){

    fun setImagenes(imags: List<String>){
        imagenes = imags
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerroViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.perro_foto_renglon, parent, false )
        return PerroViewHolder(view)

    }

    override fun onBindViewHolder(holder: PerroViewHolder, position: Int) {
        val perroUrl = imagenes[position]
        holder.bind(perroUrl)
    }

    override fun getItemCount(): Int {
       return  imagenes.size
    }


}