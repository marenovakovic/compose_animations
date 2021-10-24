package com.marko.compose_animations.lowLevel

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

private enum class BoxState { Collapsed, Expanded, }

@Composable
fun CustomUpdateTransition() {
    var state by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(targetState = state, label = "")

    AnimationShowcase(
        content = {
            val size by transition.animateDp(
                label = "",
                transitionSpec = {
                    when {
                        BoxState.Collapsed isTransitioningTo BoxState.Expanded ->
                            spring(stiffness = 50f)
                        else -> tween(durationMillis = 500)
                    }
                },
            ) { state ->
                when (state) {
                    BoxState.Collapsed -> 10.dp
                    BoxState.Expanded -> 300.dp
                }
            }
            Box(
                modifier = Modifier
                    .size(size)
                    .background(Color.Cyan),
            )
        },
        trigger = {
            Button(
                onClick = {
                    state = when (state) {
                        BoxState.Collapsed -> BoxState.Expanded
                        BoxState.Expanded -> BoxState.Collapsed
                    }
                },
            ) {
                Text(text = "Trigger")
            }
        },
    )
}