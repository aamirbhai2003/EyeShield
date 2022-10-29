package com.AS.eyeshield

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.ArrayList

class LIST_ADAPTER(var cn: Context, var plantlist: ArrayList<GlassModel>) :
    RecyclerView.Adapter<LIST_ADAPTER.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val vitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_prd_list_item, parent, false)
        return MyViewHolder(vitem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvglasstname.text = plantlist[position].plantname
        holder.tvglassprice.text = "₹"+plantlist[position].plantprice
        holder.tvbrand.text = plantlist[position].glassbrand
        //Drawable drawable = cn.getResources().getDrawable(plantlist.get(position).getPlantimg());
        holder.ivpglass.setImageResource(plantlist[position].plantimg)
        // holder.tvplantprice.setText(plantlist.get(position).getPlantimg());

        holder.vp.setText("EYESHIELD")
        val gid=plantlist[position].plantname
        holder.card.setOnClickListener(View.OnClickListener {
//            Toast.makeText(holder.vp.context,"Success",Toast.LENGTH_LONG).show()
            val i= Intent(holder.vp.context,PRD_DETAILS::class.java)
            i.putExtra("id",gid)
            i.putExtra("name",plantlist[position].plantname)
            i.putExtra("price",plantlist[position].plantprice)
            i.putExtra("image",plantlist[position].plantimg)
            holder.vp.context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        return plantlist.size
    }

     inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvglasstname: TextView
        var tvglassprice: TextView
        var ivpglass: ImageView
        var vp: TextView
        var tvbrand:TextView
        var card : CardView
        init {

            tvglasstname = itemView.findViewById(R.id.p_name)
            tvglassprice = itemView.findViewById(R.id.p_price)
            tvbrand = itemView.findViewById(R.id.p_brand)
            ivpglass = itemView.findViewById(R.id.p_image)
            vp = itemView.findViewById(R.id.vp)
            card = itemView.findViewById(R.id.prd_temp)


        }
    }
}