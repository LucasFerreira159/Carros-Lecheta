package com.app4fun.carros.domain

import com.app4fun.carros.extensions.fromJson
import com.app4fun.carros.extensions.toJson
import com.app4fun.carros.utils.HttpHelper
import okhttp3.Response

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
    //Salva um carro
    fun save(carro: Carro): Response{
        //Faz POST do Json Carro
        val json = HttpHelper.post(BASE_URL, carro.toJson())
        //Lê a resposta
        val response = fromJson<Response>(json)
        return response
    }
    //Delata um carro
    fun delete(carro: Carro): Response{
        val url = "$BASE_URL/${carro.id}"
        val json = HttpHelper.delete(url)
        val response = fromJson<Response>(json)
        return response
    }
}