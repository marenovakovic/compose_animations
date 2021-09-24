package com.marko.compose_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.marko.compose_animations.ui.theme.Compose_animationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_animationsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainPager()
                }
            }
        }
    }
}