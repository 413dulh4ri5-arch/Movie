package com.movie.core.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun convertDate(postDate: String): String {
        val formatDate = SimpleDateFormat(RELEASE_DATE_FORMAT, Locale.getDefault()).parse(postDate)
        return getCurrentDate(formatDate!!)
    }

    private fun getCurrentDate(date: Date): String {
        val format = SimpleDateFormat(CONVERT_DATE_FORMAT, Locale.getDefault())
        return format.format(date)
    }

}

private const val RELEASE_DATE_FORMAT = "yyyy-MM-dd"
private const val CONVERT_DATE_FORMAT = "dd MMMM yyyy"


