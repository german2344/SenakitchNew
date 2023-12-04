package com.example.senakitchnew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.senakitchnew.send.ProductSend

class UsuarioAdapter(
    var context: Context,
    var listausuarios: ArrayList<ProductSend>
): RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return UsuarioViewHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listausuarios.get(position)

     //   holder.tvIdUsuario.text = usuario.id.toString()
        holder.tvNombre.text = usuario.name
        holder.tvEmail.text = usuario.description

        holder.btnEditar.setOnClickListener {
            onClick?.editarProducto(usuario)
        }

//        holder.btnBorrar.setOnClickListener {
//            onClick?.borrarProducto(usuario.id)
//        }
    }

    override fun getItemCount(): Int {
        return listausuarios.size
    }

    inner class UsuarioViewHolder(itemView: View): ViewHolder(itemView) {
        val tvIdUsuario = itemView.findViewById(R.id.name) as TextView
        val tvNombre = itemView.findViewById(R.id.price) as TextView
        val tvEmail = itemView.findViewById(R.id.descripcion) as TextView
        val btnEditar = itemView.findViewById(R.id.btnEditar) as Button
        val btnBorrar = itemView.findViewById(R.id.btnBorrar) as Button
    }

    interface OnItemClicked {
        fun editarProducto(usuario: ProductSend)
        fun borrarProducto(idUsuario: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

}