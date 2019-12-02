package br.com.upowl.inventorylist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.upowl.inventorylist.R
import br.com.upowl.inventorylist.entities.ItemDateEntity
import br.com.upowl.inventorylist.ui.viewholders.ItemViewHolder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ItemAdapter(val items: List<ItemDateEntity>)  : RecyclerView.Adapter<ItemViewHolder>() {
    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_list, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    private fun toCalendar(d: String) : Calendar {
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(d)
        val cal = Calendar.getInstance()
        cal.setTime(date)
        return cal
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val item = items[position]


        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(item.date)
        val dateFormat: String = SimpleDateFormat("d MMMM yyyy").format(date)

        viewHolder.mDescription.setText(dateFormat)

        val layoutManager = LinearLayoutManager(
            viewHolder.mList.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        layoutManager.setInitialPrefetchItemCount(item.results.size)

        viewHolder.mList.layoutManager = layoutManager
        viewHolder.mList.adapter = InternalAdapter(item.results)
        viewHolder.mList.setRecycledViewPool(viewPool)

    }
}