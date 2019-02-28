package com.app4fun.carros.activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Classe que conterá métodos comuns que poderão ser acessados de outras activitys
 *
 *   ---- Adicionamos @SupressLint para que não seja preciso registrar esta classe no Manifest
 *   ---- Definimos a classe como open para seja possível herdar ela em outras activitys
 */
@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    //Propriedade para acessar contexto de qualquer lugar
    protected val context: Context get() = this

}