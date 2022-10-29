package com.AS.eyeshield

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class OH_ADAPTER(var cn: Context, var order_list: ArrayList<OH_MODEL>) :
    RecyclerView.Adapter<OH_ADAPTER.myOrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OH_ADAPTER.myOrderViewHolder {
        val vitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.oh_item, parent, false)
        return myOrderViewHolder(vitem)    }

    override fun onBindViewHolder(holder: OH_ADAPTER.myOrderViewHolder, position: Int) {
        holder.o_name.text=order_list[position].oh_m_name
        holder.o_address.text=order_list[position].oh_m_address
        holder.o_phone.text=order_list[position].oh_m_phone
        holder.o_price.text=order_list[position].oh_m_price
    }

    override fun getItemCount(): Int {
        return order_list.size
    }
    inner class myOrderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var o_name:TextView
        var o_address:TextView
        var o_phone:TextView
        var o_price:TextView
        init {
            o_name=itemView.findViewById(R.id.oh_name)
            o_address=itemView.findViewById(R.id.oh_address)
            o_phone=itemView.findViewById(R.id.oh_phone)
            o_price=itemView.findViewById(R.id.oh_price)
        }
    }
}