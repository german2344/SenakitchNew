package com.example.senakitchnew.Crud.MyCrudsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.senakitchnew.R
import com.example.senakitchnew.send.ProductSend

class MyCrudsAdapter (private val contentList: List<ProductSend>, private val onClickListener:(ProductSend) -> Unit) : RecyclerView.Adapter<MyCrudsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCrudsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyCrudsViewHolder(layoutInflater.inflate(R.layout.my_item_content, parent, false))
    }

    override fun getItemCount(): Int =  contentList.size

    override fun onBindViewHolder(holder: MyCrudsViewHolder, position: Int) {
        val item = contentList[position]
        holder.render(item, onClickListener)
    }
}