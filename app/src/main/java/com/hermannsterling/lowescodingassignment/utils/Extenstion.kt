package com.hermannsterling.lowescodingassignment.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Helped fix this bug
 * Navigation action/destination com.hermannsterling.lowescodingchallenge:id/
 * action_forecastSearchFragment_to_forecastListFragment cannot be found from the current
 * destination Destination(com.hermannsterling.lowescodingchallenge:id/forecastListFragment)
 **/
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

fun NavController.safeNavigate(
    @IdRes currentDestinationId: Int,
    @IdRes id: Int,
    args: Bundle? = null
) {
    if (currentDestinationId == currentDestination?.id) {
        navigate(id, args)
    }
}