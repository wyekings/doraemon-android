package com.wyekings.composable.compose.animations.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wyekings.common.R
import kotlinx.coroutines.delay

@Preview
@Composable
fun AnimateContentPage() {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        var uiState by remember {
            mutableStateOf<UiState>(UiState.Loading)
        }

        LaunchedEffect(uiState) {
            delay(1000)
            uiState = UiState.Loaded
        }

        AnimatedContent(
            targetState = uiState,
            label = "animatedContent",
            contentAlignment = Alignment.Center,
            transitionSpec = {
                fadeIn(tween(3000)) togetherWith fadeOut(tween(3000))
            },
        ) {
            when (it) {
                UiState.Loading -> LoadingScreen()
                UiState.Loaded -> LoadedScreen()
            }
        }

        Crossfade(
            targetState = uiState, label = "crossFade", modifier = Modifier.padding(top = 20.dp)
        ) {
            when (it) {
                UiState.Loading -> LoadingScreen()
                UiState.Loaded -> LoadedScreen()
            }
        }

        Button(onClick = {
            uiState = UiState.Loading
        }, modifier = Modifier.padding(top = 10.dp)) {
            Text(text = "Load")
        }

        var count by remember {
            mutableIntStateOf(1)
        }
        AnimatedContent(targetState = count,
            label = "count",
            modifier = Modifier.padding(top = 20.dp),
            transitionSpec = {
//                if (targetState > initialState) {
//                    slideInVertically { fullHeight -> fullHeight } + fadeIn() togetherWith
//                            slideOutVertically { fullHeight -> -fullHeight } + fadeOut() using
//                            SizeTransform(clip = false)
//                } else {
//                    slideInVertically { fullHeight -> -fullHeight } + fadeIn() togetherWith
//                            slideOutVertically { fullHeight -> fullHeight } + fadeOut() using
//                            SizeTransform(clip = false)
//                }
                val isAdd = targetState > initialState
                val towards =
                    if (isAdd) AnimatedContentTransitionScope.SlideDirection.Up else AnimatedContentTransitionScope.SlideDirection.Down
                slideIntoContainer(towards) + fadeIn() togetherWith slideOutOfContainer(towards) + fadeOut() using SizeTransform(
                    clip = false
                )
            }) { targetCount ->
            Text(text = "$targetCount", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Button(onClick = {
                count++
            }) {
                Text(text = "Increase")
            }

            Button(onClick = {
                count--
            }) {
                Text(text = "Decrease")
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {

            var expanded by remember {
                mutableStateOf(false)
            }

            AnimatedContent(
                targetState = expanded,
                label = "expanded",
                contentAlignment = Alignment.TopStart,
                transitionSpec = {
                    fadeIn(
                        tween(
                            200, 200
                        )
                    ) togetherWith fadeOut(
                        tween(
                            200,200
                        )
                    ) using SizeTransform { initialSize, targetSize ->
                        if (targetState) {
                            keyframes {
                                IntSize(targetSize.width, initialSize.height) at 300 with LinearEasing
                                durationMillis = 400
                            }
                        } else {
                            keyframes {
                                IntSize(initialSize.width, targetSize.height) at 200
                                durationMillis = 400
                            }
                        }
                    }
                },
                modifier = Modifier.padding(10.dp)
            ) { expandedTarget ->
                if (expandedTarget) {
                    Text(text = "EnterTransition defines how the target content should appear, and ExitTransition defines how the initial content should disappear. In addition to all .",
                        modifier = Modifier
                            .width(200.dp)
                            .background(Color.Green)
                            .clickable {
                                expanded = false
                            }
                            .padding(10.dp))
                } else {
                    Icon(painter = rememberVectorPainter(image = Icons.Default.Call),
                        contentDescription = "Call",
                        modifier = Modifier
                            .background(Color.Green)
                            .clickable {
                                expanded = true
                            }
                            .padding(10.dp))
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.size(width = 200.dp, height = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
        )

        Text(text = "Loading")
    }
}

@Composable
private fun LoadedScreen() {
    Image(
        painter = painterResource(id = R.drawable.ic_tree),
        contentDescription = "tree",
        modifier = Modifier
            .size(width = 200.dp, height = 80.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.FillWidth
    )
}

private sealed interface UiState {
    data object Loading : UiState
    data object Loaded : UiState

}