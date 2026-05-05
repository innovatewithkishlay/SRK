package com.fixmate.app.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * Premium UI: Glassmorphism modifier
 */

fun Modifier.glassmorphism(
    blurRadius: Int = 20,
    backgroundColor: Color = Color.White.copy(alpha = 0.1f),
    borderColor: Color = Color.White.copy(alpha = 0.2f)
): Modifier = this
    .graphicsLayer {
        clip = true
    }
    .drawWithContent {
        drawContent()
        drawRect(
            color = backgroundColor,
            blendMode = BlendMode.Overlay
        )
    }
    .blur(blurRadius.dp)
