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
import com.squareup.picasso.Picasso

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
        val context = holder.itemView.context

        val carro = carros[position]

        holder.tnome.text = carro.nome
        holder.progress.visibility = View.VISIBLE

        Picasso.with(context).load(carro.urlFoto).fit().into(holder.img,
                object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.progress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.progress.visibility = View.GONE
                    }
                })
        holder.itemView.setOnClickListener{ onClick(carro)}

    }

    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tnome: TextView
        var img: ImageView
        var progress: ProgressBar
        var cardView: CardView

        init {
            tnome = view.findViewById<TextView>(R.id.tNome)
            img = view.findViewById<ImageView>(R.id.img)
            progress = view.findViewById<ProgressBar>(R.id.progress)
            cardView = view.findViewById<CardView>(R.id.card_view)
        }
    }

    override fun getItemCount() = this.carros.size

}