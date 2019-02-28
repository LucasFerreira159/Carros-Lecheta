package com.app4fun.carros.fragments

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Classe que armazenará métodos comuns para fragments
 */
open class BaseFragment: Fragment() {

    //Recupera o context
    override fun getContext(): Context {
        //Retira o returno opcional
        return super.getContext()!!
    }
}