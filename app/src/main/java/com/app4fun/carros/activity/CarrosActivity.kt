package com.app4fun.carros.activity

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.app4fun.carros.R
import com.app4fun.carros.adapter.CarroAdapter
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.domain.CarroService
import com.app4fun.carros.domain.TipoCarro
import com.app4fun.carros.extensions.setupToolbar
import com.app4fun.carros.extensions.toast
import com.app4fun.carros.fragments.CarrosFragment
import com.app4fun.carros.listener.ClickListener
import kotlinx.android.synthetic.main.activity_carros.*
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.startActivity


class CarrosActivity : BaseActivity() {

    private var tipo: TipoCarro = TipoCarro.Classicos
    private var carros = listOf<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        //Le o tipo de argumentos
        this.tipo = intent.getSerializableExtra("tipo") as TipoCarro
        //Configura Toolbar
        setupToolbar(R.id.toolbar, tipo.name, true)
        //Adiciona o fragment no layout
        if(savedInstanceState == null){
            //Cria uma instancia do fragment, e configura argumentos
            val frag = CarrosFragment()
            //Dentre os arqumentos que foram passados para a activity, esta o tipo carro
            frag.arguments = intent.extras
            //Adiciona o fragment no layout da marcação
            supportFragmentManager.beginTransaction().add(R.id.container, frag).commit()
        }
    }

}
