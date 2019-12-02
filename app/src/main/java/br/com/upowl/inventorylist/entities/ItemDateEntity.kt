package br.com.upowl.inventorylist.entities

import java.util.*

data class ItemDateEntity (
    val date : String,
    val time: Date,
    val results : List<ItemEntity>
)