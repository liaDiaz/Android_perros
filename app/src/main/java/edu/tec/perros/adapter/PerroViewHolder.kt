package edu.tec.perros.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import edu.tec.perros.databinding.PerroFotoRenglonBinding

class PerroViewHolder(view:View): RecyclerView.ViewHolder(view)  {
    //el que une el layout con el codigo, que datos vas a desplegar en los renglones
    //une el row con el codigo

    private val binding = PerroFotoRenglonBinding.bind(view)
    fun bind(imagenstring: String){
        // otra para imagenes donde va ir la imagen, como queiro que se vea y donde quiero que se vea
        Glide.with(binding.root)
            .load(imagenstring).centerCrop()
            .into(binding.fotoPerro)

        //Picasso.get().load(imagenstring).into(binding.fotoPerro)

    }

}