package com.app4fun.carros.extensions

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadUrl(url: String?, progress: ProgressBar? = null){
    if(url == null || url.trim().isEmpty()){
        setImageBitmap(null)
        return
    }
    if(progress == null){
        Picasso.get().load(url).into(this)
    } else{
        progress.visibility = View.VISIBLE
        Picasso.get().load(url).into(this, object : Callback {
            override fun onSuccess() {
                progress.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progress.visibility = View.GONE
            }

        })
    }
}