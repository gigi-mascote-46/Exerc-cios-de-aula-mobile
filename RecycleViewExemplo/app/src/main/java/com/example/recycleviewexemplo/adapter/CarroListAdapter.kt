package com.example.recycleviewexemplo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewexemplo.R
import com.example.recycleviewexemplo.model.Carro

class CarroListAdapter(private val listaCarros: ArrayList<Carro>) :
    RecyclerView.Adapter<CarroListAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textModelo: TextView = itemView.findViewById(R.id.texto_modelo)
        val textAno: TextView = itemView.findViewById(R.id.texto_ano)
        val textCor: TextView = itemView.findViewById(R.id.texto_cor)
        val textMatricula: TextView = itemView.findViewById(R.id.texto_matricula)
        val textPreco: TextView = itemView.findViewById(R.id.texto_preco)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_carros_list, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carro = listaCarros[position]
        holder.textModelo.text = carro.modelo
        holder.textAno.text = "Ano: ${carro.ano}"
        holder.textCor.text = "Cor: ${carro.cor}"
        holder.textMatricula.text = "Matrícula: ${carro.matricula}"
        holder.textPreco.text = "Preço: €${carro.preco}"
    }

    override fun getItemCount(): Int = listaCarros.size
}
