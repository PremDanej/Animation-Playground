package com.merp.jet.animation.playground.app.utils

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.EaseInOutQuart
import androidx.compose.animation.core.EaseInOutQuint
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseInQuad
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseInQuint
import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.EaseOutSine
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.ui.unit.dp

object Constants {

    const val DURATION_MILLIS = 500
    val CONTAINER_HEIGHT = 170.dp
    val EXPANDED_SIZE = 130.dp
    val DEFAULT_SIZE = 50.dp
    val COMMON_PADDING = 8.dp
    val START_PADDING = 10.dp
    const val EXPANDED_SCALE = 1f
    const val DEFAULT_SCALE = 0.3f

    val EasingOptions = mapOf(
        "Linear" to LinearEasing,
        "LinearOutSlowIn" to LinearOutSlowInEasing,
        "FastOutSlowIn" to FastOutSlowInEasing,
        "FastOutLinearIn" to FastOutLinearInEasing,
        "Ease" to Ease,
        "EaseIn" to EaseIn,
        "EaseOut" to EaseOut,
        "EaseInOut" to EaseInOut,
        "EaseInSine" to EaseInSine,
        "EaseOutSine" to EaseOutSine,
        "EaseInOutSine" to EaseInOutSine,
        "EaseInCubic" to EaseInCubic,
        "EaseOutCubic" to EaseOutCubic,
        "EaseInOutCubic" to EaseInOutCubic,
        "EaseInQuint" to EaseInQuint,
        "EaseOutQuint" to EaseOutQuint,
        "EaseInOutQuint" to EaseInOutQuint,
        "EaseInCirc" to EaseInCirc,
        "EaseOutCirc" to EaseOutCirc,
        "EaseInOutCirc" to EaseInOutCirc,
        "EaseInQuad" to EaseInQuad,
        "EaseOutQuad" to EaseOutQuad,
        "EaseInOutQuad" to EaseInOutQuad,
        "EaseInQuart" to EaseInQuart,
        "EaseOutQuart" to EaseOutQuart,
        "EaseInOutQuart" to EaseInOutQuart,
        "EaseInExpo" to EaseInExpo,
        "EaseOutExpo" to EaseOutExpo,
        "EaseInOutExpo" to EaseInOutExpo,
        "EaseInBack" to EaseInBack,
        "EaseOutBack" to EaseOutBack,
        "EaseInOutBack" to EaseInOutBack,
        "EaseInElastic" to EaseInElastic,
        "EaseOutElastic" to EaseOutElastic,
        "EaseInOutElastic" to EaseInOutElastic,
        "EaseInBounce" to EaseInBounce,
        "EaseOutBounce" to EaseOutBounce,
        "EaseInOutBounce" to EaseInOutBounce,
    )
}