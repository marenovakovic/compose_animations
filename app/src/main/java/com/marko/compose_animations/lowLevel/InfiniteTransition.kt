package com.marko.compose_animations.lowLevel

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@Composable
fun InfiniteTransition() {
    AnimationShowcase(
        content = {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                val transition = rememberInfiniteTransition()
                val x by transition.animateFloat(
                    initialValue = 0f,
                    targetValue = maxWidth.value - 16f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 5000),
                        repeatMode = RepeatMode.Reverse,
                    ),
                )
                val y by transition.animateFloat(
                    initialValue = 0f,
                    targetValue = maxHeight.value,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 800),
                        repeatMode = RepeatMode.Reverse,
                    ),
                )
                Surface(
                    modifier = Modifier
                        .size(16.dp)
                        .offset(x = x.dp, y = y.dp),
                    shape = CircleShape,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Cyan),
                    )
                }
            }
        },
        trigger = {},
    )
}

@Preview
@Composable
fun InfiniteTransitionPreview() {
    InfiniteTransition()
}