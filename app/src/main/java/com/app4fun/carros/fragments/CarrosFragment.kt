package com.app4fun.carros.fragments


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app4fun.carros.R
import com.app4fun.carros.activity.CarroActivity
import com.app4fun.carros.adapter.CarroAdapter
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.domain.CarroService
import com.app4fun.carros.domain.TipoCarro
import com.app4fun.carros.listener.ClickListener
import kotlinx.android.synthetic.main.fragment_carros.*
import kotlinx.android.synthetic.main.include_progress.*
import org.jetbrains.anko.startActivity

@Suppress("DEPRECATION")
class CarrosFragment : BaseFragment() {

    private var tipo = TipoCarro.Classicos
    private var carros = listOf<Carro>()

    //Cria a view da fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_carros, container, false)
        //Lê os argumentos
        this.tipo = arguments?.getSerializable("tipo") as TipoCarro
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Views
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun taskCarros() {
        //Liga a animação progressBar
        progress.visibility = View.VISIBLE
        Thread{
            //Busca os carros
            this.carros = CarroService.getCarros(tipo)
            activity?.runOnUiThread {
                // Atualiza a çosta
                recyclerView.adapter = CarroAdapter(carros, object : ClickListener {
                    override fun onClick(view: View, pos: Int) {
                        val carro = carros[pos]
                        activity?.startActivity<CarroActivity>("carro" to carro)
                    }
                })
                progress.visibility = View.INVISIBLE
            }
        }.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        taskCarros()
    }


}
