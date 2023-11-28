package com.wyekings.composable.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wyekings.composeable.ripple.rememberNoRippleInteractionSource
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabPager(pages: List<Page>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val state = rememberPagerState { pages.size }
        ScrollableTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
//                .windowInsetsPadding(WindowInsets.safeDrawing)
                .safeDrawingPadding()
                .height(40.dp),
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[state.currentPage])
                        .clip(RoundedCornerShape(8.dp))
                        .padding(start = 60.dp, end = 60.dp, bottom = 10.dp),
                    height = 2.0.dp,
                    color = Color.Black
                )
            },
            selectedTabIndex = state.currentPage,
            containerColor = Color.Transparent,
        ) {
            pages.forEachIndexed { index, page ->
                val coroutineScope = rememberCoroutineScope()
                val selected = state.currentPage == index
                Tab(
                    modifier = Modifier.background(Color.Transparent),
                    selected = selected,
                    onClick = {
                        coroutineScope.launch {
                            state.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            modifier = Modifier.background(Color.Transparent),
                            text = page.title,
                            fontSize = if (selected) 14.sp else 13.sp,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                            color = if (selected) Color.Black else Color.DarkGray,
                        )
                    },
                    interactionSource = rememberNoRippleInteractionSource(),
                )
            }
        }
        HorizontalPager(state = state, modifier = Modifier.fillMaxSize()) { index ->
            pages[index].content.invoke()
        }

//        LaunchedEffect(state) {
//            snapshotFlow { state.currentPage }
//                .distinctUntilChanged()
//                .collectLatest { currentPage ->
//                    val visible = currentPage == state.currentPage
//                    if (visible) {
//                        Timber.d("visible---------------->$currentPage")
//                    } else {
//                        Timber.d("invisible---------------->$currentPage")
//                    }
//                }
//        }
    }
}

data class Page(
    val title: String, val content: @Composable () -> Unit
)