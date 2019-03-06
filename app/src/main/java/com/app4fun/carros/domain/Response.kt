package com.app4fun.carros.domain

data class Response (val id: Long, val status:String, val msg: String, val url: String){
    fun isOK() = "OK".equals(status, ignoreCase = true)
}