package br.com.jordilucas.carros.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.jordilucas.carros.R
import br.com.jordilucas.carros.domain.Carro
import br.com.jordilucas.carros.extensions.loadUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_carro.view.*

/**
 * Created by jordi on 31/08/17.
 */
class CarroAdapter(val carros:List<Carro>, val onClick:(Carro) -> Unit) :
                RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carro,
                parent, false)
        val holder = CarrosViewHolder(view)
        return holder

    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val view = holder.itemView
        val carro = carros[position]

        view.tNome.text = carro.nome
        view.progress.visibility = View.VISIBLE

        view.img.loadUrl(carro.urlFoto, view.progress)

        view.setOnClickListener{ onClick(carro)}

    }

    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

    override fun getItemCount() = this.carros.size

}