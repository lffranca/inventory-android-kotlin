package br.com.upowl.inventorylist.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.upowl.inventorylist.R
import br.com.upowl.inventorylist.entities.ItemDateEntity
import br.com.upowl.inventorylist.entities.ResultEntity
import br.com.upowl.inventorylist.ui.adapters.ItemAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_list_items.*
import okhttp3.*
import java.io.IOException
import java.util.*


class ListItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        rv_list_items.layoutManager = LinearLayoutManager(this)

        getItems()
    }

    fun getItems() {
        val url  = "https://gist.githubusercontent.com/kevinmcampos/b0f8c18fe478e11a06a1e9cb96fd3d7b/raw/53fc49f38578597eb7708da8aa5a19ca96a963bc/items"

        val request  = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create()
                val itemsFeed = gson.fromJson(body, ResultEntity::class.java)

                val items = itemsFeed.results
                    .map{it.date_created.substring(0, 10)}
                    .distinct()
                    .map { ItemDateEntity(it, Calendar.getInstance().run {
                        set(it.substring(0, 4).toInt(), it.substring(5, 7).toInt(), it.substring(8).toInt(), 0, 0, 0)
                        time
                    }, itemsFeed.results.filter { itemEntity -> itemEntity.date_created.substring(0, 10) == it }) }

                runOnUiThread {
                    rv_list_items.adapter = ItemAdapter(items)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}
