package br.com.upowl.inventorylist.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.upowl.inventorylist.R

class InternalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtItemCode: TextView = itemView.findViewById(R.id.item_code)
    val txtItemStatus: TextView = itemView.findViewById(R.id.item_status)
    val ivItemImg: ImageView = itemView.findViewById(R.id.iv_item_img)
}