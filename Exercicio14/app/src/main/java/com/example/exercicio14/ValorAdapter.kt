package com.example.exercicio14

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ValorAdapter(private val valores: List<Int>) :
    RecyclerView.Adapter<ValorAdapter.ValorViewHolder>() {

    inner class ValorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView = itemView.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_valor, parent, false)
        return ValorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ValorViewHolder, position: Int) {
        holder.textViewItem.text = valores[position].toString()
    }

    override fun getItemCount(): Int = valores.size
}
