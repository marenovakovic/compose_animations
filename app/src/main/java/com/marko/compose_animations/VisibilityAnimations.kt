package com.marko.compose_animations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.marko.compose_animations.highLevel.visibility.VisibilityChanges
import com.marko.compose_animations.highLevel.visibility.VisibilityChangesChildrenAnimation
import com.marko.compose_animations.highLevel.visibility.VisibilityChangesCustom
import com.marko.compose_animations.highLevel.visibility.VisibilityChangesObserveChanges

@Composable
fun VisibilityAnimations() {
    Column(modifier = Modifier.fillMaxSize()) {
        VisibilityChanges()
        Divider()
        VisibilityChangesObserveChanges()
        Divider()
        VisibilityChangesChildrenAnimation()
        Divider()
        VisibilityChangesCustom()
    }
}