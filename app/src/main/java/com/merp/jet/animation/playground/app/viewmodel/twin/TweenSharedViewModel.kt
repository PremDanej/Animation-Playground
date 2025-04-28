package com.merp.jet.animation.playground.app.viewmodel.twin

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.merp.jet.animation.playground.app.utils.Constants.DURATION_MILLIS

class TweenSharedViewModel : ViewModel(){

    var animationType by mutableStateOf("DP")

    var expanded by mutableStateOf(false)
        private set

    var durationMillis by mutableIntStateOf(DURATION_MILLIS)
        private set

    var easingName by mutableStateOf("Linear")
        private set

    var easing by mutableStateOf(LinearEasing)
        private set

    fun toggleExpanded() {
        expanded = !expanded
    }

    fun setDuration(duration: Int) {
        durationMillis = duration
    }

    fun setEasing(name: String, easing: Easing) {
        this.easingName = name
        this.easing = easing
    }

    fun setDefault(){
        this.expanded = false
        this.setDuration(DURATION_MILLIS)
    }
}