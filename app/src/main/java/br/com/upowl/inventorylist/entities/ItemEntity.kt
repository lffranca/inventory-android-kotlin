package br.com.upowl.inventorylist.entities

import java.util.*

data class ItemEntity (
    val code : String,
    val status : String,
    val image_url : String,
    val date_created : String
)