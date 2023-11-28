package com.example.senakitchnew.ui.comentarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.senakitchnew.R
import com.example.senakitchnew.send.ComentSend


class ComentAdapter(private val contentList: List<ComentSend>) : RecyclerView.Adapter<ComentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ComentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComentViewHolder(layoutInflater.inflate(R.layout.item_coment, parent, false))
    }


    override fun getItemCount(): Int =  contentList.size

    override fun onBindViewHolder(holder: ComentViewHolder, position: Int) {
        val item = contentList[position]
        holder.render(item)
    }

}