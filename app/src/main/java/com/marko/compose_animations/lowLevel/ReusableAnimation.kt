package com.marko.compose_animations.lowLevel

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

private enum class TransitionState { Expanded, Collapsed, }

private class TransitionData(
    color: State<Color>,
    height: State<Dp>,
) {
    val color by color
    val height by height
}

@Composable
private fun updateTransitionData(transitionState: TransitionState): TransitionData {
    val transition = updateTransition(targetState = transitionState, label = "")
    val color = transition.animateColor(label = "") { state ->
        when (state) {
            TransitionState.Collapsed -> Color.Cyan
            TransitionState.Expanded -> Color.Magenta
        }
    }
    val height = transition.animateDp(label = "") { state ->
        when (state) {
            TransitionState.Collapsed -> 56.dp
            TransitionState.Expanded -> 96.dp
        }
    }

    return remember { TransitionData(color, height) }
}

@Composable
fun ReusableAnimation() {
    var state by remember { mutableStateOf(TransitionState.Collapsed) }
    val transitionData = updateTransitionData(state)
    AnimationShowcase(
        content = {
            Box(
                modifier = Modifier
                    .background(transitionData.color)
                    .fillMaxWidth()
                    .height(transitionData.height),
            )
        },
        trigger = {
            Button(
                onClick = {
                    state = when (state) {
                        TransitionState.Collapsed -> TransitionState.Expanded
                        TransitionState.Expanded -> TransitionState.Collapsed
                    }
                },
            ) {
                Text(text = "Trigger")
            }
        },
    )
}

@Preview
@Composable
fun ReusableAnimationPreview() {
    ReusableAnimation()
}