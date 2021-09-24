package com.marko.compose_animations.animatedContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimatedContentAnimations() {
    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedContentFadeThrough()
        Divider()
        AnimatedContentCounter()
        Divider()
        AnimatedContentSizeTransform()
        Divider()
        AnimateContentAnimateContent()
        Divider()
    }
}