package com.merp.jet.animation.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.merp.jet.animation.playground.app.playground.navigation.NavigationGraph
import com.merp.jet.animation.playground.app.ui.screen.home.AnimationPlaygroundApp
import com.merp.jet.animation.playground.app.ui.screen.main.MainScreen
import com.merp.jet.animation.playground.app.ui.theme.AnimationPlaygroundAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /*AnimationPlaygroundAppTheme {
                AnimationPlaygroundApp()
            }*/

            /*Scaffold { paddingValues ->
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    NavigationGraph()
                }
            }*/

            AnimationPlaygroundAppTheme {
                Scaffold { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}