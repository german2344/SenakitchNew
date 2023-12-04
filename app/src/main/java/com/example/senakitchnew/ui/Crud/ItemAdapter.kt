package com.example.senakitchnew.ui.Crud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.senakitchnew.R
import com.example.senakitchnew.send.ProductSend
import com.example.senakitchnew.ui.home.ProductViewHolder

class ItemAdapter(private var contentList: List<ProductSend>) : RecyclerView.Adapter<ItemAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_content, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = contentList[position]
        holder.bind(item)
    }



    override fun getItemCount(): Int = contentList.size

    // Add this method to update the list of items
    fun setItems(newItems: List<ProductSend>) {
        contentList = newItems
        notifyDataSetChanged()

    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        private val Preci: TextView = itemView.findViewById(R.id.Preci)

        fun bind(item: ProductSend) {
            title.text = item.name
            descripcion.text = item.description
            Preci.text = item.price.toString()
        }
    }
}
