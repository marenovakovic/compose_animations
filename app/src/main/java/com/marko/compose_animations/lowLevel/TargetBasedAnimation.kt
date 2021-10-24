package com.marko.compose_animations.lowLevel

import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@Composable
fun TargetBasedAnimations() {
    AnimationShowcase(
        content = {
            val animation = TargetBasedAnimation(
                animationSpec = tween(),
                initialValue = 0f,
                targetValue = 400f,
                initialVelocity = 1f,
                typeConverter = Float.VectorConverter,
            )
            var playTime by remember { mutableStateOf(0L) }
            var animationValue by remember { mutableStateOf(0f) }

            LaunchedEffect(animation) {
                val startTime = withFrameNanos { it }
                do {
                    playTime = withFrameMillis { it } - startTime
                    animationValue = animation.getValueFromNanos(playTime)
                } while (true)
            }

            Box(
                modifier = Modifier
                    .height(56.dp)
                    .width(animationValue.dp),
            )
        },
        trigger = {},
    )
}