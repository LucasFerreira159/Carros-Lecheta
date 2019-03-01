package com.app4fun.carros.extensions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

//Converte o objeto para JSON
fun Any.toJson(prettyPriting: Boolean = false): String{
    val builder = GsonBuilder()
    if(prettyPriting){
        builder.setPrettyPrinting()
    }

    val json = builder.create().toJson(this)
    return json
}

//Converte JSON para objeto
inline fun <reified T> Any.fromJson(json: String): T{
    val type = object : TypeToken<T>(){}.type
    return Gson().fromJson<T>(json, type)
}