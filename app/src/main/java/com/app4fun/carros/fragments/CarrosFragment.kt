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
import com.app4fun.carros.domain.event.RefreshListEvent
import com.app4fun.carros.listener.ClickListener
import com.app4fun.carros.utils.AndroidUtils
import kotlinx.android.synthetic.main.fragment_carros.*
import kotlinx.android.synthetic.main.include_progress.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

@Suppress("DEPRECATION")
class CarrosFragment : BaseFragment() {

    private var tipo = TipoCarro.Classicos
    private var carros = listOf<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Registra no bus de eventos
        EventBus.getDefault().register(this)
    }

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
        doAsync{
            //Busca os carros
            carros = CarroService.getCarros(tipo)
            uiThread {
                // Atualiza a çosta
                recyclerView.adapter = CarroAdapter(carros, object : ClickListener {
                    override fun onClick(view: View, pos: Int) {
                        val carro = carros[pos]
                        activity?.startActivity<CarroActivity>("carro" to carro)
                    }
                })
                progress.visibility = View.INVISIBLE
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val internetOK = AndroidUtils.isNetworkAvailable(context)
        if(internetOK){
            taskCarros()
        }else{
            activity?.toast("Não há conexão com internet")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //Cancela o registro no bus de eventos
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: RefreshListEvent){
        //Recebeu o evento
        taskCarros()
    }
}
