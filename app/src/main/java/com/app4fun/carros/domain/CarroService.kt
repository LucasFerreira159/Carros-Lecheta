package com.app4fun.carros.domain

import android.content.Context
import com.app4fun.carros.R
import com.app4fun.carros.extensions.fromJson

object CarroService {
    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
        //Este e o arquivo que temos que ler
        val raw = getArquivoRaw(tipo)
        //Abre a o arquivo para leitura
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use {
            //Le o Json e cria a lista de carros
            val json = it.readText()
            val carros = fromJson<List<Carro>>(json)
            return carros
        }
    }
    //Retorna o arquivo que temos que ler para o tipo informado
    private fun getArquivoRaw(tipo: TipoCarro) = when(tipo){
        TipoCarro.Classicos -> R.raw.carros_classicos
        TipoCarro.Esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }
}