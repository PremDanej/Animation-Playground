package com.merp.jet.animation.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.merp.jet.animation.playground.app.components.TopActionBar
import com.merp.jet.animation.playground.app.navigation.AnimationPlaygroundNavigation
import com.merp.jet.animation.playground.app.ui.theme.AnimationPlaygroundAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val isFirstScreen = remember { mutableStateOf(false) }
            val screenName = remember { mutableStateOf("Animation Playground") }
            AnimationPlaygroundAppTheme {
                Scaffold(
                    topBar = {
                        TopActionBar(
                            title = screenName.value,
                            isFirstScreen = isFirstScreen.value,
                            navigateBack = { navController.popBackStack() }
                        )
                    }
                ) { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        AnimationPlaygroundNavigation(
                            navController = navController,
                            isFirstScreen = isFirstScreen,
                            name = screenName
                        )
                    }
                }
            }
        }
    }
}