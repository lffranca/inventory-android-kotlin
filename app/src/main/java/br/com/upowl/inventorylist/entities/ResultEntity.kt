package br.com.upowl.inventorylist.entities

data class ResultEntity (
    val filtered : Int,
    val total : Int,
    val results : List<ItemEntity>
)