package com.marko.compose_animations.highLevel.animatedContent

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentSizeTransform() {
    var expanded by remember { mutableStateOf(false) }

    AnimationShowcase(
        content = {
            AnimatedContent(
                modifier = Modifier.background(Color.Cyan),
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                },
            ) { targetState ->
                if (targetState) LargeContent()
                else SmallContent()
            }
        },
        trigger = {
            Button(onClick = { expanded = !expanded }) {
                Text(text = "Switch")
            }
        },
    )
}

@Composable
private fun LargeContent() {
    Column {
        Text(text = "Marko Novakovic".repeat(50))
    }
}

@Composable
private fun SmallContent() {
    Icon(imageVector = Icons.Filled.Person, contentDescription = null)
}