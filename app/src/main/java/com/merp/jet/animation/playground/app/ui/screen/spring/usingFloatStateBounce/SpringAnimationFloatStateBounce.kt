package com.merp.jet.animation.playground.app.ui.screen.spring.usingFloatStateBounce

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.merp.jet.animation.playground.app.navigation.AnimationPlaygroundScreens.SpringCodePreviewAndGeneratorScreen
import com.merp.jet.animation.playground.app.utils.Constants.AllBounceWithStiffness
import com.merp.jet.animation.playground.app.utils.Constants.COMMON_PADDING
import com.merp.jet.animation.playground.app.utils.Constants.CONTAINER_HEIGHT
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SIZE
import com.merp.jet.animation.playground.app.utils.Constants.START_PADDING
import com.merp.jet.animation.playground.app.viewmodel.spring.SpringViewModel
import kotlinx.coroutines.delay

@Composable
fun SpringAnimationFloatStateBounceScreen(
    navController: NavHostController,
    viewModel: SpringViewModel
) {
    val bounceDetails = listOf("HighBouncy", "MediumBouncy", "LowBouncy", "RegularBouncy")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(COMMON_PADDING),
    ) {
        AllBounceWithStiffness.entries.forEachIndexed { index, (dampingRatio, stiffnessList) ->
            val bounceLabel = bounceDetails.getOrNull(index) ?: "Bouncy"
            stiffnessList.forEach { (stiffnessName, stiffnessValue) ->
                BouncingAnimationFloatState(
                    label = "$bounceLabel to $stiffnessName",
                    dampingRatio = dampingRatio,
                    stiffness = stiffnessValue
                ){
                    viewModel.bouncy = dampingRatio
                    viewModel.stiffness = stiffnessValue
                    viewModel.bouncyName = "$bounceLabel to $stiffnessName"
                    navController.navigate(SpringCodePreviewAndGeneratorScreen.name)
                }
            }
        }
    }
}

@Composable
fun BouncingAnimationFloatState(
    label: String,
    dampingRatio: Float,
    stiffness: Float,
    onClick: () -> Unit = {}
) {
    val shape = MaterialTheme.shapes.medium
    var scaleTarget by remember { mutableFloatStateOf(EXPANDED_SCALE) }

    val scaleAnimation by animateFloatAsState(
        targetValue = scaleTarget,
        animationSpec = spring(
            dampingRatio = dampingRatio,
            stiffness = stiffness,
        ),
        label = "BoxSize"
    )

    // Auto bounce back and forth
    LaunchedEffect(scaleTarget) {
        delay(1500) // Time between each bounce
        scaleTarget = if (scaleTarget == EXPANDED_SCALE) DEFAULT_SCALE else EXPANDED_SCALE
    }

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
                text = "$label spring",
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
                        .scale(scaleAnimation)
                        .background(MaterialTheme.colorScheme.onBackground, shape)
                )
            }
        }
    }
}