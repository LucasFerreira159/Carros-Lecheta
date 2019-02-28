package com.app4fun.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.app4fun.carros.domain.TipoCarro
import com.app4fun.carros.fragments.CarrosFragment

class TabsAdapter (private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    //Qtd Fragment
    override fun getCount(): Int = 3


    //Retorna o tipo pela posição
    private fun getTipoCarro(position: Int) = when(position){
        0 -> TipoCarro.Classicos
        1 -> TipoCarro.Esportivos
        else -> TipoCarro.Luxo
    }

    //Titulo tab
    override fun getPageTitle(position: Int): CharSequence? {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)

    }

    //Fragment que vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        val arguments = Bundle()
        arguments.putSerializable("tipo", tipo)
        f.arguments = arguments
        return f
    }


}