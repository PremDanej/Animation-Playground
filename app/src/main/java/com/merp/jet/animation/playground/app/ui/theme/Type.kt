package com.merp.jet.animation.playground.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography().run {
    val fontFamily = FontFamily.Serif
    val lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )

    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        displayMedium = displayMedium.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        displaySmall = displaySmall.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        titleLarge = titleLarge.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        titleMedium = titleMedium.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        titleSmall = titleSmall.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        bodySmall = bodySmall.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        labelLarge = labelLarge.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        labelMedium = labelMedium.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
        labelSmall = labelSmall.copy(fontFamily = fontFamily, lineHeightStyle = lineHeightStyle),
    )
}