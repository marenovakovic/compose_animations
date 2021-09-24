package com.marko.compose_animations.animatedContent

import androidx.compose.animation.animateContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.marko.compose_animations.AnimationShowcase

@Composable
fun AnimateContentAnimateContent() {
    var showLargeMessage by remember { mutableStateOf(false) }

    AnimationShowcase(
        content = {
            Text(
                modifier = Modifier.animateContentSize(),
                text = "Marko Novakovic".repeat(10),
                overflow = TextOverflow.Ellipsis,
                maxLines = if (showLargeMessage) 10 else 2,
            )
        },
        trigger = {
            Button(onClick = { showLargeMessage = !showLargeMessage }) {
                Text(text = "Expand text")
            }
        },
    )
}