package com.merp.jet.animation.playground.app.ui.screen.twin.usingFloatStateEasing

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.merp.jet.animation.playground.app.navigation.AnimationPlaygroundScreens.TweenCodePreviewAndGeneratorScreen
import com.merp.jet.animation.playground.app.viewmodel.twin.TweenSharedViewModel
import com.merp.jet.animation.playground.app.utils.Constants.COMMON_PADDING
import com.merp.jet.animation.playground.app.utils.Constants.CONTAINER_HEIGHT
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.DURATION_MILLIS
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.EasingOptions
import com.merp.jet.animation.playground.app.utils.Constants.START_PADDING
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TweenAnimationFloatStateScreen(navController: NavHostController, viewModel: TweenSharedViewModel) {
    LazyColumn(
        contentPadding = PaddingValues(COMMON_PADDING),
        modifier = Modifier.fillMaxSize()
    ) {
        items(EasingOptions.toList()) { (key, easing) ->
            AnimationFloatState(easingName = key, easing = easing){
                viewModel.setEasing(key, easing)
                viewModel.animationType = "FLOAT"
                navController.navigate(TweenCodePreviewAndGeneratorScreen.name)
            }
        }
    }
}

@Composable
fun AnimationFloatState(
    easingName: String,
    easing: Easing,
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

    val animation by animateFloatAsState(
        targetValue = if (expanded) EXPANDED_SCALE else DEFAULT_SCALE,
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
                modifier = Modifier.size(CONTAINER_HEIGHT),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(EXPANDED_SIZE)
                        .scale(animation)
                        .background(MaterialTheme.colorScheme.onBackground, shape)
                )
            }
        }
    }
}