package com.merp.jet.animation.playground.app.ui.screen.twin.usingFloatStateInfinite

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.merp.jet.animation.playground.app.utils.Constants.COMMON_PADDING
import com.merp.jet.animation.playground.app.utils.Constants.CONTAINER_HEIGHT
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.DURATION_MILLIS
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.RepeatOption
import com.merp.jet.animation.playground.app.utils.Constants.START_PADDING

@Composable
fun TweenAnimationFloatStateInfiniteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(COMMON_PADDING),
    ) {
        RepeatOption.keys.forEach { modeName ->
            val repeatMode = RepeatOption.getValue(modeName)
            RepeatAnimationFloatState(modeName = modeName, repeatMode = repeatMode)
        }
    }
}

@Composable
fun RepeatAnimationFloatState(
    modeName: String,
    repeatMode: RepeatMode
) {
    val shape = MaterialTheme.shapes.medium
    val transition = rememberInfiniteTransition(label = "InfiniteTransition")
    val animatedScale by transition.animateFloat(
        initialValue = DEFAULT_SCALE,
        targetValue = EXPANDED_SCALE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = DURATION_MILLIS,
                delayMillis = DURATION_MILLIS,
            ),
            repeatMode = repeatMode
        ), label = "AnimatedScale"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(CONTAINER_HEIGHT)
            .padding(COMMON_PADDING),
        border = BorderStroke(0.7.dp, Color.LightGray),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = START_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "► ",
            )
            Text(
                text = "$modeName mode",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier.size(CONTAINER_HEIGHT),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(EXPANDED_SIZE)
                        .scale(animatedScale)
                        .background(MaterialTheme.colorScheme.onBackground, shape)
                )
            }
        }
    }
}