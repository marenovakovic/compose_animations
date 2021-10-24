package com.marko.compose_animations.lowLevel

import androidx.compose.animation.*
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityAnimatedContent() {
    var isSelected by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isSelected, label = "")
    AnimationShowcase(
        content = {
            val color by transition.animateColor(label = "") { state ->
                if (state) Color.Magenta else Color.Cyan
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, color))
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                transition.AnimatedVisibility(
                    visible = { targetState -> targetState },
                    enter = slideInVertically(),
                    exit = slideOutVertically(),
                ) {
                    Text(text = "This will animate in/out")
                }
                transition.AnimatedContent(
                    transitionSpec = {
                        if (targetState) {
                            slideInHorizontally({ width -> -width }) + fadeIn() with
                                    slideOutHorizontally({ width -> width }) + fadeOut()
                        } else {
                            slideInHorizontally({ width -> width }) + fadeIn() with
                                    slideOutHorizontally({ width -> -width }) + fadeOut()
                        }.using(SizeTransform(clip = false))
                    },
                ) { selected ->
                    Text(text = if (selected) "In" else "Out")
                }
            }
        },
        trigger = {
            Button(onClick = { isSelected = !isSelected }) {
                Text(
                    modifier = Modifier.animateContentSize(),
                    text = if (isSelected) "Click me" else "Click me again",
                )
            }
        },
    )
}