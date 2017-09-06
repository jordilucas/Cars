package br.com.jordilucas.carros.extensions

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by jordi on 05/09/17.
 */
fun ImageView.loadUrl(url: String?, progress:ProgressBar? = null){
    if(url == null || url.trim().isEmpty()){
        setImageBitmap(null)
        return
    }
    if(progress == null){
        Picasso.with(context).load(url).fit.into(this)
    }
    else{
        progress.visibility = View.VISIBLE

        Picasso.with(context).load(url).fit().into(this,
                object : Callback {

                    override fun onSuccess() {
                        progress.visibility = View.GONE
                    }

                    override fun onError() {
                        progress.visibility = View.GONE
                    }

                })

    }
}