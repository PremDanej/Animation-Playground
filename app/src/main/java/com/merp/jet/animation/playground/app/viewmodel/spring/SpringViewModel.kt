package com.merp.jet.animation.playground.app.viewmodel.spring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SCALE

class SpringViewModel : ViewModel() {

    var expanded by mutableStateOf(false)

    var bouncy by mutableFloatStateOf(DEFAULT_SCALE)

    var stiffness by mutableFloatStateOf(0f)

    var bouncyName by mutableStateOf("NoBouncy")

    fun toggleExpanded() {
        expanded = !expanded
    }
}