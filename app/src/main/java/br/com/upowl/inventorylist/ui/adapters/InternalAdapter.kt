package br.com.upowl.inventorylist.ui.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.upowl.inventorylist.R
import br.com.upowl.inventorylist.entities.ItemEntity
import br.com.upowl.inventorylist.ui.viewholders.InternalViewHolder
import br.com.upowl.inventorylist.util.getStatusFromLang
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class InternalAdapter(val items: List<ItemEntity>)  : RecyclerView.Adapter<InternalViewHolder>() {
    val hander: Handler = Handler()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InternalViewHolder {
        val context = viewGroup?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.internal_item_list, viewGroup, false)

        return InternalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(viewHolder: InternalViewHolder, position: Int) {
        val item = items[position]
        viewHolder.txtItemCode.text = item.code

        val status = viewHolder.txtItemStatus.context.resources.getString(getStatusFromLang(item.status))
        viewHolder.txtItemStatus.text = status

        val thread = Thread(Runnable {
            try {
                val url = URL(item.image_url)
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                val input: InputStream = conn.getInputStream()
                val img = BitmapFactory.decodeStream(input)

                hander.post(Runnable {
                    viewHolder.ivItemImg.setImageBitmap(img)
                })
            } catch (e: Exception) {
                Log.e("ERROR", e.message)
            }
        })

        thread.start()
    }
}