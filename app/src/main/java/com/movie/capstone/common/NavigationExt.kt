package com.movie.capstone.common

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import java.lang.IllegalArgumentException

fun NavController.navigateSafe(directions: NavDirections) {
    try {
        navigate(directions)
    } catch (ex: IllegalArgumentException) {
        ex.printStackTrace()
    }
}
