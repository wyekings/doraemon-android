package com.wyekings.sunflower.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyekings.composeable.ripple.rememberNoRippleInteractionSource
import com.wyekings.sunflower.theme.SunflowerTheme
import kotlinx.coroutines.launch

/**
 *  @author wye on 9/7/23
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val pages = SunflowerPage.entries.toList()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pages.size
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onFilterClick = { /*TODO*/ },
                showFilter = true,
                topAppBarScrollBehavior = scrollBehavior,
            )
        },
    ) {
        HomePagerScreen(
            pagerState = pagerState,
            modifier = Modifier.padding(it),
            pages = pages
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    pages: List<SunflowerPage> = SunflowerPage.entries.toList()
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(2.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(horizontal = 60.dp)
                        .background(MaterialTheme.colorScheme.onBackground)
                )
            },
            divider = {
                // remove the default divider
            },
        ) {
            pages.forEachIndexed { index, page ->
                val title = stringResource(id = page.titleResId)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page = index)
                        }
                    },
                    text = { Text(text = title) },
                    icon = {
                        Icon(
                            painter = painterResource(id = page.iconResId),
                            contentDescription = title
                        )
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    interactionSource = rememberNoRippleInteractionSource()
                )
            }
        }

        HorizontalPager(state = pagerState) { index ->
            when (pages[index]) {
                SunflowerPage.MY_GARDEN -> {
                    MyGardenScreen()
                }

                SunflowerPage.PLANT_LIST -> {
                    PlantListScreen()
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    SunflowerTheme {
        HomeScreen()
    }
}