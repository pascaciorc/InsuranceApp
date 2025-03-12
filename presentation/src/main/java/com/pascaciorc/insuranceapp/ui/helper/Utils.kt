package com.pascaciorc.insuranceapp.ui.helper

import com.pascaciorc.insuranceapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateFormat(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formattedDate = format.format(date)
    return formattedDate
}

fun Int.toDrawable(): Int {
    return when (this) {
        0 -> R.drawable.person
        1 -> R.drawable.airplane
        2 -> R.drawable.pet
        3 -> R.drawable.car
        else -> R.drawable.drop_down
    }
}