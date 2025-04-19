package com.merp.jet.animation.playground.app.ui.screen.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val easingOptions = mapOf(
        "Linear" to LinearEasing,
        "EaseIn" to EaseIn,
        "EaseOut" to EaseOut,
        "FastOutSlowIn" to FastOutSlowInEasing
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        easingOptions.keys.forEach { key ->
            AnimationDpState(easingName = key, easing = easingOptions.getValue(key))
        }
        easingOptions.keys.forEach { key ->
            AnimationFloatState(easingName = key, easing = easingOptions.getValue(key))
        }
        Spacer(modifier = Modifier.height(10.dp))
        AnimationFloatStateSpring("")
    }
}

@Composable
fun AnimationDpState(easingName: String, easing: Easing) {
    var expanded by remember { mutableStateOf(false) }
    val durationMillis = 500
    val containerHeight = 160.dp
    val defaultSize = 50.dp
    val expandableSize = 130.dp
    val shape = MaterialTheme.shapes.medium

    LaunchedEffect(expanded) {
        launch {
            delay(1500)
            expanded = !expanded
        }
    }

    val animatedSize by animateDpAsState(
        targetValue = if (expanded) expandableSize else defaultSize,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = easing
        ), label = "BoxSize"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(containerHeight)
            .padding(5.dp),
        border = BorderStroke(0.7.dp, Color.LightGray),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "► $easingName easing",
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(containerHeight),
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
fun AnimationFloatState(easingName: String, easing: Easing) {

    var expanded by remember { mutableStateOf(false) }
    val durationMillis = 500
    val containerHeight = 160.dp
    val defaultSize = 50.dp
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
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = 0,
            easing = easing
        ), label = "BoxSize"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(containerHeight)
            .padding(5.dp),
        border = BorderStroke(0.7.dp, Color.LightGray),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "► $easingName easing",
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(containerHeight),
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
fun AnimationFloatStateSpring(easingName: String) {

    var expanded by remember { mutableStateOf(false) }
    val durationMillis = 500
    val containerHeight = 160.dp
    val defaultSize = 50.dp
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
            .height(containerHeight)
            .padding(5.dp),
        border = BorderStroke(0.7.dp, Color.LightGray),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "► $easingName easing",
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(containerHeight),
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
fun LinearEasingAnimation(modifier: Modifier = Modifier, easing: Easing) {
    var expanded by remember { mutableStateOf(false) }
    val durationMillis = 600
    //val easing = LinearEasing
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(expanded) {
        launch {
            //expanded = !expanded
            delay(1500)
            expanded = !expanded
        }
    }

    val animatedSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = easing
        ), label = "BoxSize"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(animatedSize)
                .background(Color.Blue, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Tap Me", color = Color.White, fontSize = 16.sp)
        }
    }
}