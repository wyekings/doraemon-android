package com.wyekings.composable.compose.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wyekings.composable.ui.TopBar
import timber.log.Timber

@Composable
fun AnimatableScreen() {

    var animationSpec: AnimationSpec<Dp> = snap()
    var animateType by remember { mutableStateOf("Snap") }

    Scaffold(
        topBar = {
            AnimatableTopBar { type, spec ->
                animateType = type
                animationSpec = spec
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {

            var big by remember { mutableStateOf(false) }
            val target = if (big) 256.dp else 96.dp
            val margin by animateDpAsState(
                targetValue = target, label = "animation1", animationSpec = animationSpec
            ) {
                Timber.d("finished=$it")
            }

            Box(
                modifier = Modifier
                    .background(Color(0xFF6200EE))
                    .size(margin)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = animateType, fontSize = 13.sp, color = Color.White)
            }


            Button(
                onClick = { big = !big },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(bottom = 20.dp),
            ) {
                Text(text = "Toggle")
            }

            BoomAnimation()

            var decay by remember { mutableStateOf(false) }
            val decayAnim = remember { Animatable(initialValue = 0.dp, Dp.VectorConverter) }
            var padding by remember {
                mutableStateOf(decayAnim.value)
            }
            val decaySpec = remember { exponentialDecay<Dp>() }
            LaunchedEffect(decay) {
//                decayAnim.snapTo(0.dp)
                val result = decayAnim.animateDecay(initialVelocity = 1000.dp, decaySpec) {
                    padding = decayAnim.value
                }
            }
//            Timber.d("decayValue=${decayAnim.value}")
            Box(
                modifier = Modifier
                    .padding(top = decayAnim.value)
                    .background(Color(0xFF6200EE))
                    .size(56.dp)
                    .align(Alignment.TopStart)
                    .clickable {
                        decay = !decay
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Decay", fontSize = 13.sp, color = Color.White)
            }

            Box(
                modifier = Modifier
                    .padding(top = padding)
                    .background(Color(0xFF6200EE))
                    .size(56.dp)
                    .align(Alignment.TopStart)
                    .clickable {
                        decay = !decay
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Follow", fontSize = 13.sp, color = Color.White)
            }
        }
    }
}

@Composable
private fun BoxScope.BoomAnimation() {
    val boomAnim = remember { Animatable(48.dp, Dp.VectorConverter, label = "boom") }
    var boom by remember { mutableStateOf(false) }
    LaunchedEffect(boom) {
        boomAnim.animateTo(
            48.dp, animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMedium,
            ), initialVelocity = 1000.dp
        ) {
            // block will be invoked on each animation frame.
            Timber.d("boom=${this.value}")
        }
    }

    Box(
        modifier = Modifier
            .background(Color(0xFF6200EE))
            .size(boomAnim.value)
            .align(Alignment.Center)
            .clickable {
                boom = !boom
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Boom", fontSize = 13.sp, color = Color.White)
    }
}

@Composable
private fun AnimatableTopBar(onSelect: (String, AnimationSpec<Dp>) -> Unit) {
    var showDropdownMenu by remember { mutableStateOf(false) }
    TopBar(title = "Animatable", actions = {
        IconButton(onClick = { showDropdownMenu = !showDropdownMenu }) {
            Icon(
                imageVector = Icons.Default.MoreVert, contentDescription = "DropdownMenu"
            )
        }
        DropdownMenu(
            expanded = showDropdownMenu,
            onDismissRequest = { showDropdownMenu = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(text = {
                Text(text = "Snap", fontSize = 12.sp)
            }, onClick = {
                showDropdownMenu = false
                onSelect.invoke(
                    "Snap", snap(
                        delayMillis = 200
                    )
                )
            })

            val tween = "Tween"
            DropdownMenuItem(text = {
                Text(text = tween, fontSize = 12.sp)
            }, onClick = {
                showDropdownMenu = false
                onSelect.invoke(
                    tween, tween(easing = FastOutSlowInEasing),
                )
            })

            val keyframes = "Keyframes"
            DropdownMenuItem(text = {
                Text(text = keyframes, fontSize = 12.sp)
            }, onClick = {
                showDropdownMenu = false
                onSelect.invoke(
                    keyframes,
                    keyframes {
                        durationMillis = 750
                        delayMillis = 0
                        0.dp at 250 with LinearOutSlowInEasing
                        128.dp at 250 with FastOutSlowInEasing
                        384.dp at 500 with FastOutLinearInEasing
                        256.dp at 750 with LinearEasing
                    },
                )
            })

            val repeatable = "Repeatable"
            DropdownMenuItem(text = {
                Text(text = repeatable, fontSize = 12.sp)
            }, onClick = {
                showDropdownMenu = false
                onSelect.invoke(
                    repeatable, repeatable(iterations = 3, animation = tween()),
                )

//                infiniteRepeatable()
            })

            val spring = "Spring"
            DropdownMenuItem(text = {
                Text(text = spring, fontSize = 12.sp)
            }, onClick = {
                showDropdownMenu = false
                onSelect.invoke(
                    spring,
                    spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                )
            })
        }
    })
}

@Preview(showBackground = true)
@Composable
private fun AnimationsScreenPreview() {
    AnimatableScreen()
}