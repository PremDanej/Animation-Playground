package com.merp.jet.animation.playground.app.ui.screen.twin.usingDpStateEasing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.merp.jet.animation.playground.app.ui.screen.twin.TweenSharedViewModel
import com.merp.jet.animation.playground.app.utils.Constants.COMMON_PADDING
import com.merp.jet.animation.playground.app.utils.Constants.CONTAINER_HEIGHT
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.DURATION_MILLIS
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.EasingOptions
import com.merp.jet.animation.playground.app.utils.Constants.START_PADDING
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TweenAnimationDpStateScreen(navController: NavHostController , viewModel: TweenSharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(COMMON_PADDING),
    ) {
        // List out all easing animation
        EasingOptions.keys.forEach { key ->
            AnimationDpState(easingName = key)
        }
    }
}

@Composable
fun AnimationDpState(
    easingName: String,
    onClick: () -> Unit = {}
) {
    val shape = MaterialTheme.shapes.medium
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(expanded) {
        launch {
            delay(1500)
            expanded = !expanded
        }
    }

    AnimatedVisibility(visible = true) {
        val animatedSize by animateDpAsState(
            targetValue = if (expanded) EXPANDED_SIZE else DEFAULT_SIZE,
            animationSpec = tween(
                durationMillis = DURATION_MILLIS,
                easing = EasingOptions.getValue(easingName)
            ), label = "BoxSize"
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(CONTAINER_HEIGHT)
                .padding(COMMON_PADDING),
            border = BorderStroke(0.7.dp, Color.LightGray),
            shape = shape,
            onClick = onClick
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
                    text = "$easingName easing",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier.size(CONTAINER_HEIGHT),
                ) {
                    Box(
                        modifier = Modifier
                            .size(animatedSize)
                            .background(MaterialTheme.colorScheme.onBackground, shape)
                            .align(Alignment.Center),
                    )
                }
            }
        }
    }
}