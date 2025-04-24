package com.merp.jet.animation.playground.app.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopActionBar(
    title: String,
    isFirstScreen: Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior,
    navigateBack: () -> Unit
) {

    val centralColor = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
    )
    CenterAlignedTopAppBar(
        colors = centralColor,
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
    /*if (!isFirstScreen) {

        val colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
            //scrolledContainerColor = Color.White
        )

        MediumTopAppBar(
            colors = colors,
            title = { Text(text = title) },
            navigationIcon = {
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
            },
            scrollBehavior = scrollBehavior
        )
    } else {

        val centralColor = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Animation Playground",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            colors = centralColor
        )
    }*/
}