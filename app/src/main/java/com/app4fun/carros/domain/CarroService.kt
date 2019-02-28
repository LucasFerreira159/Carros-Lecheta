package com.app4fun.carros.domain

import android.content.Context

object CarroService {


    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
        val tipoString = context.getString(tipo.string)
        //Cria um array vazio de carros
        val carros = mutableListOf<Carro>()
        //Cria 20 carros
        for (i in 1..20) {
            val c = Carro()
            //Nome do do carro dinâmico
            c.nome = "Carro $tipoString $i"
            c.desc = "Desc $i"
            //URL da foto fixa por enquanto
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png"
            carros.add(c)
        }
        return carros
    }

}