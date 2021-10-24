package com.marko.compose_animations.lowLevel

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

private enum class ChildState { Collapsed, Expanded, }

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun CreateChildTransition() {
    var state by remember { mutableStateOf(ChildState.Collapsed) }
    AnimationShowcase(
        content = {
            val transition = updateTransition(targetState = state, label = "")
            Column {
                FirstPart(
                    isExpanded = transition.createChildTransition { state ->
                        state == ChildState.Expanded
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                SecondPart(
                    isExpanded = transition.createChildTransition { state ->
                        state == ChildState.Expanded
                    },
                )
            }
        },
        trigger = {
            Button(
                onClick = {
                    state = when (state) {
                        ChildState.Collapsed -> ChildState.Expanded
                        ChildState.Expanded -> ChildState.Collapsed
                    }
                },
            ) {
                Text(text = "Trigger")
            }
        },
    )
}

@Composable
private fun FirstPart(isExpanded: Transition<Boolean>) {
    val height by animateDpAsState(targetValue = if (isExpanded.targetState) 96.dp else 48.dp)
    Box(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .height(height),
    )
}

@Composable
private fun SecondPart(isExpanded: Transition<Boolean>) {
    val height by animateDpAsState(targetValue = if (isExpanded.targetState) 96.dp else 48.dp)
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxWidth()
            .height(height),
    )
}