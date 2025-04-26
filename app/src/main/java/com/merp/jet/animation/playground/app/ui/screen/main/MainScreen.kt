package com.merp.jet.animation.playground.app.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merp.jet.animation.playground.app.navigation.AnimationPlaygroundScreens.*
import com.merp.jet.animation.playground.app.utils.Constants.COMMON_PADDING
import com.merp.jet.animation.playground.app.utils.Constants.CONTAINER_HEIGHT
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.DURATION_MILLIS
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.EasingOptions
import com.merp.jet.animation.playground.app.utils.Constants.START_PADDING
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(COMMON_PADDING)
    ) {
        AnimationDpStateEasing(easingName = "DpState", easing = FastOutSlowInEasing, onClick = {
            navController.navigate(TweenUsingDpStateEasingScreen.name)
        })
        AnimationFloatStateEasing(
            easingName = "FloatState",
            easing = FastOutSlowInEasing,
            onClick = {
                navController.navigate(TweenUsingFloatStateEasingScreen.name)
            })
        AnimationFloatStateSpring(easingName = "FloatState", onClick = {
            navController.navigate(SpringUsingFloatStateStiffScreen.name)
        })
        AnimationFloatStateInfinite(easingName = "Infinite FloatState", onClick = {
            navController.navigate(TweenUsingFloatStateInfiniteScreen.name)
        })
        AnimationColorStateEasing(
            easingName = "Infinite FloatState",
            onClick = {
                navController.navigate(TweenUsingColorStateEasingScreen.name)
            })
    }
}

@Composable
fun AnimationDpStateEasing(easingName: String, easing: Easing, onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val shape = MaterialTheme.shapes.medium

    LaunchedEffect(expanded) {
        launch {
            delay(1500)
            expanded = !expanded
        }
    }

    val animatedSize by animateDpAsState(
        targetValue = if (expanded) EXPANDED_SIZE else DEFAULT_SIZE,
        animationSpec = tween(
            durationMillis = DURATION_MILLIS,
            easing = easing
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

@Composable
fun AnimationFloatStateEasing(easingName: String, easing: Easing, onClick: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val shape = MaterialTheme.shapes.medium

    LaunchedEffect(expanded) {
        launch {
            delay(1500)
            expanded = !expanded
        }
    }

    val animation by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.3f,
        animationSpec = tween(
            durationMillis = DURATION_MILLIS,
            delayMillis = 0,
            easing = easing
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
                modifier = Modifier
                    .size(CONTAINER_HEIGHT),
            ) {
                Box(
                    modifier = Modifier
                        .size(EXPANDED_SIZE)
                        .scale(animation)
                        .background(MaterialTheme.colorScheme.onBackground, shape)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun AnimationFloatStateInfinite(easingName: String, onClick: () -> Unit) {

    val shape = MaterialTheme.shapes.medium

    val animation by rememberInfiniteTransition(label = "InfiniteTransition").animateFloat(
        initialValue = DEFAULT_SCALE,
        targetValue = EXPANDED_SCALE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = DURATION_MILLIS,
                delayMillis = DURATION_MILLIS,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "Repeat"
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
                modifier = Modifier
                    .size(CONTAINER_HEIGHT),
            ) {
                Box(
                    modifier = Modifier
                        .size(EXPANDED_SIZE)
                        .scale(animation)
                        .background(MaterialTheme.colorScheme.onBackground, shape)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun AnimationFloatStateSpring(easingName: String, onClick: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val expandableSize = 130.dp
    val shape = MaterialTheme.shapes.medium

    LaunchedEffect(expanded) {
        launch {
            delay(1500)
            expanded = !expanded
        }
    }

    val animation by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.3f,
        animationSpec = spring(
            dampingRatio = 1f,
            stiffness = Spring.StiffnessMedium,
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
                text = "$easingName spring",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(CONTAINER_HEIGHT),
            ) {
                Box(
                    modifier = Modifier
                        .size(expandableSize)
                        .scale(animation)
                        .background(MaterialTheme.colorScheme.onBackground, shape)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun AnimationColorStateEasing(
    easingName: String,
    onClick: () -> Unit = {}
) {
    val shape = MaterialTheme.shapes.medium
    //var expanded by remember { mutableStateOf(false) }

    var isRed by remember { mutableStateOf(true) }


    LaunchedEffect(isRed) {
        launch {
            delay(1500)
            isRed = !isRed
        }
    }

    AnimatedVisibility(visible = true) {
        val color by animateColorAsState(
            targetValue = if (isRed) Color.Red else Color.Green,
            animationSpec = tween(
                durationMillis = DURATION_MILLIS,
            ), label = "ColorAnimation"
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
                            .size(EXPANDED_SIZE)
                            .clip(shape)
                            .background(color)
                            .align(Alignment.Center),
                    )
                }
            }
        }
    }
}