package com.marko.compose_animations.visibility

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityChangesObserveChanges() {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimationShowcase(
        content = {
            AnimatedVisibility(
                visibleState = state,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
            ) {
                Text("This will disappear/appear")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = when {
                    state.isIdle && state.currentState -> "Visible"
                    state.isIdle && !state.currentState -> "Invisible"
                    !state.isIdle && state.currentState -> "Disappearing"
                    else -> "Appearing"
                }
            )
        },
        trigger = {},
    )
}