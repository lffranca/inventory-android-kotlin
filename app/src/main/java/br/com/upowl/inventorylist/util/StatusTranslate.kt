package br.com.upowl.inventorylist.util

import br.com.upowl.inventorylist.R

fun getStatusFromLang(value: String) : Int {
    return when(value) {
        "AVAILABLE" -> R.string.available
        "DELIVERED" -> R.string.delivered
        "LOST" -> R.string.lost
        "IN_REPAIR" -> R.string.in_repair
        else -> 0
    }
}
