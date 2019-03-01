package com.app4fun.carros.activity

import android.os.Bundle
import com.app4fun.carros.R
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.extensions.loadUrl
import com.app4fun.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.include_activity_carro.*


class CarroActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        //Lê objeto enviado por parâmetro
        val carro by lazy { intent.getParcelableExtra<Carro>("carro") }
        //Configura toolbar
        setupToolbar(R.id.toolbar, carro.nome, true)
        //Atualiza descrição do carro
        tDesc.text = carro.desc
        //Mostra a foto do carro
        appBarImg.loadUrl(carro.urlFoto)
    }
}
