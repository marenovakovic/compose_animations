package com.marko.compose_animations.animatedContent

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentCounter() {
    var count by remember { mutableStateOf(0) }

    AnimationShowcase(
        content = {
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    if (targetState < initialState) {
                        slideInVertically({ height -> height }) + fadeIn() with
                                slideOutVertically({ height -> -height }) + fadeOut()
                    } else {
                        slideInVertically({ height -> -height }) + fadeIn() with
                                slideOutVertically({ height -> height }) + fadeOut()
                    }
                        .using(SizeTransform(clip = false))
                },
            ) { targetState ->
                Text(
                    text = "$targetState",
                    fontSize = 32.sp,
                )
            }
        },
        trigger = {
            Row {
                Button(onClick = { count++ }) {
                    Text(text = "Increment")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { count-- }) {
                    Text(text = "Decrement")
                }
            }
        },
    )
}