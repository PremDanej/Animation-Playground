package com.merp.jet.animation.playground.app.ui.screen.twin.codeGenerator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.merp.jet.animation.playground.app.components.VerticalSpacer
import com.merp.jet.animation.playground.app.utils.Constants.DEFAULT_SCALE
import com.merp.jet.animation.playground.app.utils.Constants.EXPANDED_SCALE
import com.merp.jet.animation.playground.app.viewmodel.twin.TweenSharedViewModel
import kotlinx.coroutines.launch

@Composable
fun TweenCodePreviewAndGeneratorScreen(viewModel: TweenSharedViewModel) {
    NavigationTabs(viewModel)
}

@Composable
fun NavigationTabs(viewModel: TweenSharedViewModel) {
    val scope = rememberCoroutineScope()
    val tabs = listOf("View", "Code")
    val pagerState = rememberPagerState(pageCount = tabs::size)
    val selectedTab = remember { derivedStateOf { pagerState.currentPage } }.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) })
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) { index ->
            when (index) {
                0 -> AnimationControls(viewModel)
                1 -> GeneratedCodePreview(viewModel)
            }
        }
    }
}

@Composable
fun AnimationControls(viewModel: TweenSharedViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedBox(viewModel)
        VerticalSpacer()
        Text("Duration: ${viewModel.durationMillis} ms", fontWeight = FontWeight.Medium)
        Slider(
            value = viewModel.durationMillis.toFloat(),
            onValueChange = { viewModel.setDuration(it.toInt()) },
            valueRange = 300f..3000f,
            steps = 8
        )
        Text("Easing: ${viewModel.easingName}", fontWeight = FontWeight.Medium)
    }
}

@Composable
fun AnimatedBox(viewModel: TweenSharedViewModel) {
    if (viewModel.animationType == "DP") {
        val animatedSize by animateDpAsState(
            targetValue = if (viewModel.expanded) 200.dp else 100.dp,
            animationSpec = tween(
                durationMillis = viewModel.durationMillis,
                easing = viewModel.easing
            ), label = "BoxSize"
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .clickable { viewModel.toggleExpanded() },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(animatedSize)
                    .background(Color.Blue, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text("Tap Me", color = Color.White, fontSize = 16.sp)
            }
        }
    } else if (viewModel.animationType == "FLOAT") {
        val animatedScale by animateFloatAsState(
            targetValue = if (viewModel.expanded) EXPANDED_SCALE else DEFAULT_SCALE,
            animationSpec = tween(
                durationMillis = viewModel.durationMillis,
                easing = viewModel.easing
            ), label = "BoxSize"
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .clickable { viewModel.toggleExpanded() },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(animatedScale)
                    .background(Color.Blue, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text("Tap Me", color = Color.White, fontSize = 16.sp)
            }
        }
    }

}

@Composable
fun GeneratedCodePreview(viewModel: TweenSharedViewModel) {
    var codeText by remember {
        mutableStateOf<String?>(null)
    }

    if (viewModel.animationType == "DP") {
        codeText = """
                 // Box expanded
                 var expanded by remember { mutableStateOf(false) }
                 
                 // If expand, Change box size
                 val animatedSize by animateDpAsState(
                     targetValue = if (expanded) 200.dp else 100.dp,
                     animationSpec = tween(
                         durationMillis = ${viewModel.durationMillis},
                         easing = ${viewModel.easingName}
                     )
                 )
                 
                 // Box code
                 Box(
                     modifier = Modifier
                         .fillMaxSize()
                         .clickable { expanded = !expanded }
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
             """.trimIndent()
    } else if (viewModel.animationType == "FLOAT") {
        codeText = """
                 // Box expanded
                 var expanded by remember { mutableStateOf(false) }
                 
                 // If expand, Change box size
                 val animatedScale by animateFloatAsState(
                     targetValue = if (expanded) 1f else 0.3f,
                     animationSpec = tween(
                         durationMillis = ${viewModel.durationMillis},
                         easing = ${viewModel.easingName}
                     )
                 )
                 
                 // Box code
                 Box(
                     modifier = Modifier
                         .fillMaxSize()
                         .clickable { expanded = !expanded }
                 ) {
                     Box(
                         modifier = Modifier
                             .size(200.dp)
                             .scale(animatedScale)
                             .background(Color.Blue, RoundedCornerShape(16.dp)),
                         contentAlignment = Alignment.Center
                     ) {
                         Text("Tap Me", color = Color.White, fontSize = 16.sp)
                     }
                 }
             """.trimIndent()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(0.7.dp, Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(10.dp)
                    .size(20.dp)
            )
            codeText?.let { code ->
                Text(
                    text = code,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}