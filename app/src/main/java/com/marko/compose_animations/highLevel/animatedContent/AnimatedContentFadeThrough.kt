package com.marko.compose_animations.highLevel.animatedContent

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentFadeThrough() {
    var count by remember { mutableStateOf(0) }

    AnimationShowcase(
        content = {
            AnimatedContent(targetState = count) { targetState ->
                val color = if (targetState.mod(2) == 0) Color.Cyan else Color.Magenta
                Box(
                    modifier = Modifier
                        .height(96.dp)
                        .fillMaxWidth()
                        .background(color),
                )
            }
        },
        trigger = {
            Button(onClick = { count++ }) {
                Text(text = "Trigger")
            }
        },
    )
}