package com.merp.jet.animation.playground.app.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.merp.jet.animation.playground.app.navigation.AnimationPlaygroundScreens.*
import com.merp.jet.animation.playground.app.viewmodel.twin.TweenSharedViewModel
import com.merp.jet.animation.playground.app.ui.screen.main.MainScreen
import com.merp.jet.animation.playground.app.ui.screen.spring.codeGenerator.SpringCodePreviewAndGeneratorScreen
import com.merp.jet.animation.playground.app.ui.screen.twin.usingDpStateEasing.TweenAnimationDpStateScreen
import com.merp.jet.animation.playground.app.ui.screen.twin.usingFloatStateEasing.TweenAnimationFloatStateScreen
import com.merp.jet.animation.playground.app.ui.screen.twin.codeGenerator.TweenCodePreviewAndGeneratorScreen
import com.merp.jet.animation.playground.app.ui.screen.twin.usingFloatStateInfinite.TweenAnimationFloatStateInfiniteScreen
import com.merp.jet.animation.playground.app.ui.screen.spring.usingFloatStateBounce.SpringAnimationFloatStateBounceScreen
import com.merp.jet.animation.playground.app.viewmodel.spring.SpringViewModel

@Composable
fun AnimationPlaygroundNavigation(
    navController: NavHostController,
    name: MutableState<String>,
    isFirstScreen: MutableState<Boolean>
) {
    val viewModel: TweenSharedViewModel = viewModel()
    val springViewModel: SpringViewModel = viewModel()

    fun defaultScreenConfig(isMain : Boolean = false, title: String){
        name.value = title
        isFirstScreen.value = isMain
    }

    NavHost(
        modifier = Modifier.animateContentSize(),
        navController = navController,
        startDestination = MainScreen.name,
    ) {
        composable(route = MainScreen.name) {
            defaultScreenConfig(isMain = true, title = "Animation Playground")
            MainScreen(navController = navController)
        }
        composable(route = TweenUsingDpStateEasingScreen.name) {
            defaultScreenConfig(title = "Tween - animateAsDpState()")
            TweenAnimationDpStateScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = TweenUsingFloatStateEasingScreen.name) {
            defaultScreenConfig(title = "Tween - animateAsFloatState()")
            TweenAnimationFloatStateScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = TweenCodePreviewAndGeneratorScreen.name) {
            defaultScreenConfig(title = "Animation Preview")
            TweenCodePreviewAndGeneratorScreen(viewModel = viewModel)
        }
        composable(route = TweenUsingFloatStateInfiniteScreen.name) {
            defaultScreenConfig(title = "Infinite Animation")
            TweenAnimationFloatStateInfiniteScreen()
        }
        composable(route = SpringUsingFloatStateBounceScreen.name) {
            defaultScreenConfig(title = "Spring - animateAsFloatState()")
            SpringAnimationFloatStateBounceScreen(navController = navController, viewModel = springViewModel)
        }
        composable(route = SpringCodePreviewAndGeneratorScreen.name) {
            defaultScreenConfig(title = "Animation Preview")
            SpringCodePreviewAndGeneratorScreen(viewModel = springViewModel)
        }
    }
}