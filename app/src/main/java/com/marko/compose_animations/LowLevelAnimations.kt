package com.marko.compose_animations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.marko.compose_animations.lowLevel.*

@Composable
fun LowLevelAnimations() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatableAnimation()
        Divider()
        CustomUpdateTransition()
        Divider()
        CreateChildTransition()
        Divider()
        AnimatedVisibilityAnimatedContent()
        Divider()
        ReusableAnimation()
        Divider()
        InfiniteTransition()
        Divider()
        TargetBasedAnimations()
        Divider()
    }
}