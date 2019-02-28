package com.app4fun.carros.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.app4fun.carros.R
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.extensions.loadUrl
import com.app4fun.carros.listener.ClickListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_carro.view.*
import java.lang.Exception

class CarroAdapter(val carros: List<Carro>,  var clickListener: ClickListener) :
    RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {

    //Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CarrosViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_carro, viewGroup, false)
        return CarrosViewHolder(view, clickListener)
    }

    //Retorna quantidade de carros na lista
    override fun getItemCount() = carros.size

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val context = holder.itemView.context
        //Recupera o objeto carro
        val carro = carros[position]
        holder.bindView(carro)

    }


    //ViewHolder
    inner class CarrosViewHolder(itemView: View, listener: ClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var listener:ClickListener? = null

        init {
            this.listener = listener
            itemView.setOnClickListener(this)
        }

        fun bindView(carro: Carro){
            //views do layout
            var tNome: TextView = itemView.nome_adapter
            var img: ImageView = itemView.img_adapter
            var progress: ProgressBar = itemView.progress_adapter
            var cardView: CardView = itemView.card_view

            //Atualiza os dados do carro
            tNome.text = carro.nome
            progress.visibility = View.VISIBLE
            //Faz o download da foto e mostra o ProgressBar
            img.loadUrl(carro.urlFoto, progress)
        }


        override fun onClick(v: View) {
            this.listener?.onClick(v, adapterPosition)
        }

    }
}