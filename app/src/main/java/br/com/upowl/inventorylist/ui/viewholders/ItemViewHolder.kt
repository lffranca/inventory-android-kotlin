package br.com.upowl.inventorylist.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.upowl.inventorylist.R

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mDescription: TextView = itemView.findViewById(R.id.description)
    val mList: RecyclerView = itemView.findViewById(R.id.list_internal)
}