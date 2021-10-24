package com.marko.compose_animations.highLevel.visibility

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marko.compose_animations.AnimationShowcase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityChangesChildrenAnimation() {
    var visible by remember { mutableStateOf(true) }

    AnimationShowcase(
        content = {
            AnimatedVisibility(
                visible = visible,
                enter = EnterTransition.None,
                exit = ExitTransition.None,
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = fadeIn(),
                                exit = fadeOut(),
                            )
                            .height(32.dp)
                            .fillMaxWidth()
                            .background(color = Color.Magenta),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInHorizontally(),
                                exit = slideOutHorizontally(),
                            )
                            .height(32.dp)
                            .fillMaxWidth()
                            .background(color = Color.Cyan),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInVertically(),
                                exit = slideOutVertically(),
                            )
                            .height(32.dp)
                            .fillMaxWidth()
                            .background(color = Color.Blue),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = fadeIn() + slideInHorizontally(),
                                exit = fadeOut() + slideOutHorizontally(),
                            )
                            .height(32.dp)
                            .fillMaxWidth()
                            .background(color = Color.Red),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        },
        trigger = {
            Button(onClick = { visible = !visible }) {
                Text(text = "Re trigger")
            }
        },
    )
}