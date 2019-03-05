package com.app4fun.carros.domain

import com.app4fun.carros.extensions.fromJson
import com.app4fun.carros.utils.HttpHelper

object CarroService {
    private const val BASE_URL = "http://livrowebservices.com.br/rest/carros"
    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(tipo: TipoCarro): List<Carro> {
        //Cria a url para o tipo informado
        val url = "$BASE_URL/tipo/${tipo.name}"
        //Faz a requisição GET no WebServices
        val json = HttpHelper.get(url)
        //Cria a lista de carros a partir do JSON
        val carros = fromJson<List<Carro>>(json)
        return carros
    }

}