package com.merp.jet.animation.playground.app.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopActionBar(
    title: String,
    isFirstScreen: Boolean = false,
    navigateBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(
            elevation = 4.dp,
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if(isFirstScreen.not()) {
                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier
                        .size(35.dp)
                )
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "BackIcon"
                    )
                }
            }
        }
    )
}