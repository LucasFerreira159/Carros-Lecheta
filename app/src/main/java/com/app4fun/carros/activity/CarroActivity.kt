package com.app4fun.carros.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.app4fun.carros.R
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.extensions.loadUrl
import com.app4fun.carros.extensions.setupToolbar
import com.app4fun.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.include_activity_carro.*
import org.jetbrains.anko.startActivity


class CarroActivity : BaseActivity() {

    //Lê objeto enviado por parâmetro
    val carro by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        //Configura toolbar
        setupToolbar(R.id.toolbar, carro.nome, true)
        //Atualiza descrição do carro
        tDesc.text = carro.desc
        //Mostra a foto do carro
        appBarImg.loadUrl(carro.urlFoto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_editar -> {
                //Abre a tela de cadastro (editar carro)
                //Passa o carro como parâmetro
                startActivity<CarroFormActivity>("carro" to carro)
                finish()
            }
            R.id.action_excluir -> {
                toast("deletar o carro")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
