package com.marko.compose_animations.visibility

import androidx.compose.animation.*
import androidx.compose.animation.core.animateSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityChangesCustom() {
    BoxWithConstraints {
        var visible by remember { mutableStateOf(true) }

        AnimationShowcase(
            content = {
                AnimatedVisibility(
                    visible = visible,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None,
                ) {
                    val background by transition.animateColor(label = "") { state ->
                        if (state == EnterExitState.Visible) Color.Cyan else Color.Magenta
                    }
                    val size by transition.animateSize(label = "") { state ->
                        if (state == EnterExitState.Visible)
                            Size(
                                this@BoxWithConstraints.constraints.maxWidth.toFloat(),
                                96f,
                            )
                        else Size(0f, 0f)
                    }
                    Box(
                        modifier = Modifier
                            .size(size.width.dp, size.height.dp)
                            .background(background),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("This will disappear/appear", color = Color.Black)
                    }
                }
            },
            trigger = {
                Button(onClick = { visible = !visible }) {
                    Text(text = "Change visibility")
                }
            },
        )
    }
}