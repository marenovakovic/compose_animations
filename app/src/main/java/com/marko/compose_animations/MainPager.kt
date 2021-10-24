package com.marko.compose_animations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.marko.compose_animations.highLevel.animatedContent.AnimatedContentAnimations
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPager() {
    val pagerState = rememberPagerState(pageCount = 3)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
        ) {
            (0 until pagerState.pageCount).forEach { position ->
                val text = when (position) {
                    0 -> "Visibility"
                    1 -> "Content"
                    2 -> "Low level"
                    else -> throw IllegalStateException()
                }
                Tab(
                    text = { Text(text = text) },
                    selected = position == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(position)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) { position ->
            when (position) {
                0 -> VisibilityAnimations()
                1 -> AnimatedContentAnimations()
                2 -> LowLevelAnimations()
            }
        }
    }
}