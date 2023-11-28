package com.example.senakitchnew.ui.Platos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.senakitchnew.R
import com.example.senakitchnew.send.PlatosSend

class PlatosAdapter(private val contentList: List<PlatosSend>) : RecyclerView.Adapter<PlatosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  PlatosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlatosViewHolder(layoutInflater.inflate(R.layout.item_platos, parent, false))
    }


    override fun getItemCount(): Int =  contentList.size

    override fun onBindViewHolder(holder: PlatosViewHolder, position: Int) {
        val item = contentList[position]
        holder.render(item)
    }

}