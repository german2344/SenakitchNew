package com.example.senakitchnew.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.senakitchnew.R
import com.example.senakitchnew.send.PlatosSend
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.ui.Platos.PlatosViewHolder



class ProductAdapter(private val contentList: List<ProductSend>, private val onClickListener:(ProductSend ) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_content, parent, false))
    }


    override fun getItemCount(): Int =  contentList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = contentList[position]
        holder.render(item, onClickListener)
    }
        private var listaDeDatos: List<ProductSend> = listOf()

        fun setData(newData: List<ProductSend>) {
            listaDeDatos = newData
            notifyDataSetChanged()
        }
}