package com.marko.compose_animations.visibility

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityChanges() {
    var visible by remember { mutableStateOf(true) }

    AnimationShowcase(
        content = {
            AnimatedVisibility(visible = visible) {
                Text("This will disappear/appear")
            }
        },
        trigger = {
            Button(onClick = { visible = !visible }) {
                Text(text = "Change visibility")
            }
        },
    )
}