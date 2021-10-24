package com.marko.compose_animations.lowLevel

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.exponentialDecay
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

private enum class AnimatableMethod {
    AnimateTo,
    SnapTo,
    AnimateDecay,
}

private operator fun AnimatableMethod.not(): AnimatableMethod =
    when (this) {
        AnimatableMethod.AnimateTo -> AnimatableMethod.SnapTo
        AnimatableMethod.SnapTo -> AnimatableMethod.AnimateDecay
        AnimatableMethod.AnimateDecay -> AnimatableMethod.AnimateTo
    }

@Composable
fun AnimatableAnimation() {
    var method by remember { mutableStateOf(AnimatableMethod.AnimateTo) }
    var color by remember { mutableStateOf(Color.Cyan) }

    fun changeColor() {
        color = if (color == Color.Cyan) Color.Magenta else Color.Cyan
    }

    val animatable = remember { Animatable(Color.Blue) }

    LaunchedEffect(method) {
        when (method) {
            AnimatableMethod.AnimateTo -> animatable.animateTo(color)
            AnimatableMethod.SnapTo -> animatable.snapTo(color)
            AnimatableMethod.AnimateDecay ->
                animatable.animateDecay(animatable.velocity, exponentialDecay()) {
                    color = value
                }
        }
        changeColor()
        println(color)
    }

    AnimationShowcase(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(animatable.value)
                    .height(96.dp),
            )
        },
        trigger = {
            Button(onClick = { method = !method }) {
                Text(text = (!method).toString())
            }
        },
    )
}