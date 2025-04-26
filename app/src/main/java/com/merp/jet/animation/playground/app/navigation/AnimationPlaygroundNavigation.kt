package com.merp.jet.animation.playground.app.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.merp.jet.animation.playground.app.navigation.AnimationPlaygroundScreens.MainScreen
import com.merp.jet.animation.playground.app.ui.screen.main.MainScreen
import com.merp.jet.animation.playground.app.ui.screen.twin.TweenSharedViewModel

@Composable
fun AnimationPlaygroundNavigation(
    navController: NavHostController,
    name: MutableState<String>,
    isFirstScreen: MutableState<Boolean>
) {
    val viewModel: TweenSharedViewModel = viewModel()

    fun defaultScreenConfig(isMain : Boolean = false, title: String){
        name.value = title
        isFirstScreen.value = isMain
        if(isMain.not()){
            viewModel.setDefault()
        }
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
    }
}