package com.app4fun.carros.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.app4fun.carros.R
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.domain.CarroService
import com.app4fun.carros.domain.event.RefreshListEvent
import com.app4fun.carros.extensions.loadUrl
import com.app4fun.carros.extensions.setupToolbar
import com.app4fun.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.include_activity_carro.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread


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
                //Mostra o aleta de confirmação
                alert("Confirma excluir este carro?"){
                    title = "Alert"
                    positiveButton(R.string.sim) {taskDeletar()}
                    negativeButton(R.string.nao) { }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //Deleta o carro
    private fun taskDeletar(){
        doAsync {
            val response = CarroService.delete(carro)
            uiThread {
                //Dispara o evento para atualizar a lista de carros
                EventBus.getDefault().post(RefreshListEvent())
                finish()
            }
        }
    }
}
