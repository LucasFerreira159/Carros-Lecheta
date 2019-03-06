package com.app4fun.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.app4fun.carros.R
import com.app4fun.carros.domain.Carro
import com.app4fun.carros.domain.CarroService
import com.app4fun.carros.extensions.setupToolbar
import com.app4fun.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_carro_form_contents.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CarroFormActivity : BaseActivity() {

    val carro: Carro? by lazy { intent.getParcelableExtra<Carro>("carro")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro_form)
        //Configura a Toolbar
        val title = carro?.nome ?: getString(R.string.novo_carro);
        setupToolbar(R.id.toolbar, title, true)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_salvar -> taskSalvar()
        }
        return super.onOptionsItemSelected(item)
    }

    //Cria a thread para salvar o carro
    private fun taskSalvar(){
        doAsync {
            val c = getCarroForm()
            //Salva o carro
            val response = CarroService.save(c)
            uiThread {
                toast(response.message())
                finish()
            }
        }
    }

    //Cria um carro com os valores do formulario
    fun getCarroForm():  Carro{
        val c = carro?: Carro()
        c.tipo = getTipo()
        c.nome = tNome.text.toString()
        c.desc = tDesc.text.toString()
        return c
    }

    //Convert o valor do Radio para String
    fun getTipo(): String{
        when(tTipo.checkedRadioButtonId){
            R.id.tipoEsportivo -> return getString(R.string.esportivos)
            R.id.tipoLuxo -> return getString(R.string.luxo)
        }
        return getString(R.string.classicos)
    }
}
